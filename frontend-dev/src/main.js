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
        isConnected = true;
        store.commit('setSocketConnection', isConnected)
    };

    ws.onclose = (e) => {
        console.log("Closing websocket...");
    };
    window.setInterval(() => {
        if(store.state.outgoingMessages.length > 0){
            sendSomething(store.state.outgoingMessages.shift());
        }
    }, 1);
    console.log("Socket connected...");
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    isConnected = false;
    store.commit('setSocket', ws)
    console.log("Disconnected socket");
}

function sendSomething(msgObject) {
    console.log("sending msg", msgObject);
    ws.send(JSON.stringify(msgObject));
}

function handleMessage(message) {
    switch (message.type) {
        case 'Bids':
            store.commit('appendBid', message.msgObject);
            break;
        case 'Message':
            store.commit('addChatMsg', message.msgObject);
            break;
        case 'MessageHistory':
            store.commit('setChatHistory', message.msgObject);
            break;
        default:
            console.log("error in msg", message);
            break;
    }
}
