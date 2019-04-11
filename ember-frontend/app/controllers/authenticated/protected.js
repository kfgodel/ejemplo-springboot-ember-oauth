import Controller from '@ember/controller';
import {inject as service} from '@ember/service';

export default Controller.extend({
  session: service(),

  actions:{
    callHello(){
      fetch('/api/v1/hello', {
        headers:{
          'Authorization': `Bearer ${this.get('session.data.authenticated.access_token')}`
        }
      }).then((response)=>{
        response.text()
          .then(bodyText => this.set('bodyText', bodyText));
      })
    }
  }
});
