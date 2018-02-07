import Route from '@ember/routing/route';
import RSVP from 'rsvp';


export default Route.extend({

  model(){
    return RSVP.hash({
      employees: this.get('store').findAll('employee'),
    });
  },

  setupController: function(controller, model) {
    this._super(controller, model);
    controller.set('employees', model.employees);
  }
});
