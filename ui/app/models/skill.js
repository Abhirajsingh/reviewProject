import DS from 'ember-data';

export default DS.Model.extend({
employee: DS.hasMany('employee'),
  name:DS.attr(),
});
