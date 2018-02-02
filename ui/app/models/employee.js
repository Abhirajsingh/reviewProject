import DS from 'ember-data';

export default DS.Model.extend({
  firstName : DS.attr(),
  middleName : DS.attr(),
  lastName : DS.attr(),
  name : DS.attr(),
  employeeRole :DS.attr(),
  location : DS.belongsTo('location'),
  startDate : DS.attr("date"),
  profilePic :DS.attr(),
  email:DS.attr(),
  mobileNo : DS.attr(),
  biodata : DS.attr(),
  skills :DS.hasMany('skill'),
  department :DS.belongsTo('department'),
  project : DS.belongsTo('project'),
  reportingEmployeeName : DS.attr(),
  reportingEmployeeId : DS.attr(),
});
