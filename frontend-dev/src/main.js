import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import vueAwesomeCountdown from 'vue-awesome-countdown'
Vue.use(vueAwesomeCountdown, 'vac');
Vue.use(BootstrapVue);
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

setInterval(() => {
    if(store.state.socket == null){
        setTimeout(()=> {
                console.log("Socket was rebooted");
                store.dispatch('connectSocket');
        }, 500);
    }
}, 1000);
