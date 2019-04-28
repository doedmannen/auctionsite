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


let ws;
let isConnected = false;
connect();

function connect() {
    ws = new WebSocket('ws://localhost:8080/websocket');
    ws.onmessage = (e) => {
        let msgObject = JSON.parse(e.data);
        console.log(msgObject);
        handleMessage(msgObject);
    }
    ws.onopen = (e) => {
        sendSomething();
        isConnected = true;
        store.commit('setSocket', isConnected)
    };

    ws.onclose = (e) => {
        console.log("Closing websocket...");
    };

  console.log("Connecting...");
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    isConnected = false;
    store.commit('setSocket', isConnected)
    console.log("Disconnected");
}

function sendSomething() {
    ws.send(JSON.stringify({firstname: "Hello World!" }));
}

function handleMessage(message) {
    switch (message.type) {
        case 'Bids':
            store.commit('appendBid', message.msgObject);
            break;
        case 'Message':
            store.commit('addChatMsg', message.msgObject);
            break;
        default:
            console.log("error in msg",message);
            break;
    }
}
