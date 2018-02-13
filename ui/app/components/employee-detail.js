import Component from '@ember/component';
import {inject as service} from '@ember/service';
import Ember from'ember';

export default Component.extend({
  emailValidation: [{
    message: 'Please provide email in a valid format',
    validate: (inputValue) => {
      let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
      return emailPattern.test(inputValue);
    }
  }],

  store:  Ember.inject.service(),
  selectedSkills : [],
  reEmployee :{
    reportingEmployeeName: null,
  },
  profilePicPath: "",
  newEmployee: {
    firstName: '',
    middleName: '',
    lastName: '',
    department : null,
    project : null,
    location:null,
    skills: [],
    mobileNo: '',
    email: '',
    employeeRole:'',
    startDate: null,
    biodata: '',
    reportingEmployeeName: null,
    reportingEmployeeId:null,
    reportingEmployee: null,
  },

  router:service('router'),

  actions:{
     submit(){
       if(this.newEmployee.id){
         this.newEmployee.save();
         this.get('router').transitionTo('employee-portal');
       }
       else {

         let self = this;
         let companyEmployee = this.get('store').createRecord("employee", {
           firstName: self.newEmployee.firstName,
           middleName: self.newEmployee.middleName,
           lastName: self.newEmployee.lastName,
           email: self.newEmployee.email,
           mobileNo: self.newEmployee.mobileNo,
           biodata: self.newEmployee.biodata,
           startDate: self.newEmployee.startDate,
           profilePic: self.profilePicPath,
         });
         companyEmployee.set('employeeRole', self.newEmployee.employeeRole.get('name'));
         companyEmployee.set('location', self.newEmployee.location);
         companyEmployee.set('department', self.newEmployee.department);
         companyEmployee.set('project', self.newEmployee.project);
         companyEmployee.set('skills', self.newEmployee.skills);
         if(self.newEmployee.reportingEmployeeName){
           companyEmployee.set('reportingEmployeeName', self.newEmployee.reportingEmployeeName);
         companyEmployee.set('reportingEmployeeId', self.newEmployee.reportingEmployeeName.id);}
         companyEmployee.save();
         this.get('router').transitionTo('employee-portal');
        // window.location.reload(true);
       }
    },
     fileUpload(event){
       let self = this;
       let file = event.element.querySelectorAll("input[type=file]")[0].files[0];
       let fileModel = this.get('store').createRecord('file');
       fileModel.set('attachment' , file);
       fileModel.save().then(function(response){
       document.getElementById("#profilePic").src = "/"+ response.get("filePath");
        if(self.newEmployee.profilePic != null){
         self.newEmployee.profilePic = response.get("filePath");}
         else
           self.profilePicPath  = response.get("filePath");
       });
     },

    setEmployeeRole(e){
      this.newEmployee.employeeRole = e.get('name');
    },
    setReportingEmployee(event){
      //let id = this.newEmployee.id;
      console.log(event);
      let employee = this.get('store').peekRecord('employee',this.newEmployee.id );
      employee.set('reportingEmployeeName', this.newEmployee.reportingEmployeeName);
      console.log(this.newEmployee.get('reportingEmployeeName') , this.newEmployee.reportingEmployeeName.id);
      employee.set('reportingEmployeeId', this.newEmployee.reportingEmployeeName.id);
  },
    cancle(){
      this.get('router').transitionTo('employee-portal');
      window.location.reload(true);
    },
  }
});
