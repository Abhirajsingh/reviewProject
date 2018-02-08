import EmberRouter from '@ember/routing/router';
import config from './config/environment';

const Router = EmberRouter.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('employee-portal');
  this.route('detail', {path: '/:employee_id'});
  this.route('new-employee');
  this.route('edit-employee', {path: 'edit/:employee_id'});
});

export default Router;
