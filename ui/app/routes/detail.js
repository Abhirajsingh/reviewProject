import Route from '@ember/routing/route';
export default Route.extend({
  model(param){
    return this.get('store').findRecord('employee' ,param.employee_id);
  },
});
