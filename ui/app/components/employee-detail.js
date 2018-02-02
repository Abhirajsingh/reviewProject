import Component from '@ember/component';
import {inject as service} from '@ember/service';
import Ember from'ember';

export default Component.extend({
  store:  Ember.inject.service(),
  selectedSkills : [],
  newEmployee: {
    firstName: '',
    middleName: '',
    lastName: '',
    department : null,
    project : null,
    location: null,
    skills: [],
    mobileNo: '',
    email: '',
    employeeRole:null,
    picPath: '',
    startDate: '',
    biodata: '',
    reportingEmployee: null,
  },

  router:service('router'),

  actions:{

    saveEmployee(){
      let self = this;
      let companyEmployee = this.get('store').createRecord("employee",{
        firstName: self.newEmployee.firstName,
        middleName: self.newEmployee.middleName,
        lastName: self.newEmployee.lastName,
        startDate: self.newEmployee.startDate,
        email: self.newEmployee.email,
        mobileNo: self.newEmployee.mobileNo,
        biodata: self.newEmployee.biodata,
        profilePic : self.newEmployee.picPath,
      });
      companyEmployee.set('employeeRole', self.newEmployee.employeeRole.get('name'));
      companyEmployee.set('location' , self.newEmployee.location);
      companyEmployee.set('department' , self.newEmployee.department);
      companyEmployee.set('project' , self.newEmployee.project);
      companyEmployee.set('skills' , self.selectedSkills);
      companyEmployee.set('reportingEmployeeId' , self.newEmployee.reportingEmployee.id);
      companyEmployee.save();
      this.get('router').transitionTo('employee-portal');
    },
     fileUpload(event){
       let self = this;
       let file = event.element.querySelectorAll("input[type=file]")[0].files[0];
       let fileModel = this.get('store').createRecord('file');
       fileModel.set('attachment' , file);
       fileModel.save().then(function(response){
         self.newEmployee.picPath = response.get("filePath");
       });
     },
    cancle(){
      this.get('router').transitionTo('employee-portal');
    },
  }
});
