import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
Vue.use(BootstrapVue)
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


let wsaa;
let isConnected = false;
connect();

function connect() {
    wsaa = new WebSocket('ws://localhost:8080/websocketa');
    wsaa.onmessage = (e) => {
      showsaaomething(e.data);
    }
    wsaa.onopen = (e) => {
        sendSomething();
        isConnected = true;
    };

    wsaa.onclose = (e) => {
        console.log("Closing websocket...");
    };

  console.log("Connecting...");
}

function disconnect() {
    if (wsaa != null) {
        wsaa.close();
    }
    isConnected = false;
    console.log("Disconnected");
}

function sendSomething() {
    wsaa.send(JSON.stringify({firstname: "Hello World!" }));
}

function showsaaomething(message) {
    document.querySelector("#messages").innerText += message + "\n";
}
