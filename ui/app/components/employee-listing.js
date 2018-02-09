import Component from '@ember/component';
import {inject as service} from '@ember/service';
export default Component.extend({
  router:service('router'),
  actions:{
    getDetail(employee){
      this.get('router').transitionTo('detail',employee);
    }
  }
});
