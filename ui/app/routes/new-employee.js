import Route from '@ember/routing/route';
import RSVP from 'rsvp';

export default Route.extend({
  model(){
    return RSVP.hash({
      employees: this.get('store').findAll('employee'),
      location : this.get('store').findAll('location'),
      project :this.get('store').findAll('project'),
      department: this.get('store').findAll('department'),
      skills : this.get('store').findAll('skill'),
      roles : this.get('store').findAll('role'),
    });
  },
  setupController: function(controller, model) {
    this._super(controller, model);
    controller.set('totalEmployee',this.get('model.employees.length'));
    controller.set('employees', model.employees);
    controller.set('location' , model.location);
    controller.set('project' ,model.project);
    controller.set('department',model.department);
    controller.set('skills' , model.skills);
    controller.set('roles' , model.roles);
  }

});
