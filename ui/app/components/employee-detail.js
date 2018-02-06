import Component from '@ember/component';
import {inject as service} from '@ember/service';
import Ember from'ember';

export default Component.extend({
  store:  Ember.inject.service(),
  selectedSkills : [],
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
         companyEmployee.set('reportingEmployeeId', self.newEmployee.reportingEmployeeName.id);
         companyEmployee.set('reportingEmployeeName', self.newEmployee.reportingEmployeeName);
         companyEmployee.save();
         this.get('router').transitionTo('employee-portal');
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
    updateEmployee(){

      newEmployee.save();
    },

    setEmployeeRole(e){
      this.newEmployee.employeeRole = e.get('name');
    },
    cancle(){
      this.get('router').transitionTo('employee-portal');
    },
  }
});
