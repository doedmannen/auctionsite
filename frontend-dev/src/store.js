import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
const API_URL = "/api/";

export default new Vuex.Store({

    state: {
        currentUploads: [],
        auctionPosts: [],
        categories: [],
        me: null,
        chatMessages: [],
        socketConnected: false,
        activeChat: null,
        socket: null,
        outgoingMessages: [],
        showChat: false,
        notifications: [],
        pushes: [],
        onlineList: []
    },
    mutations: {
        setOnlineList(state, value){
            state.onlineList = value;
            // console.log(state.onlineList);
        },
        addUpload(state, value) {
            state.currentUploads.push(value);
        },
        clearUploads(state, value) {
            state.currentUploads.length = 0;
        },
        orderUploads(state, value){
            let tmp = state.currentUploads.splice(value.index, 1)[0];
            state.currentUploads.splice(value.newIndex, 0, tmp);
        },
        setPosts(state, value) {
            state.auctionPosts = value;
        },
        setCategories(state, value) {
            state.categories = value;
        },
        setMe(state, value){
            state.me = value;
        },
        addChatMsg(state, value){
            if(!state.chatMessages.filter(m => m.id == value.id).length){
                // console.log("New message");
                state.chatMessages.push(value);
            } else {
                // console.log("Replacement");
                let old = state.chatMessages.filter(m => m.id == value.id)[0];
                let index = state.chatMessages.indexOf(old);
                state.chatMessages.splice(index, 1);
                state.chatMessages.push(value);
            }
        },
        setSocketConnection(state, value){
            state.socketConnected = value;
        },
        appendBid(state, value){
           
            state.auctionPosts
                .filter(ap => value.auctionid == ap.auctionid)[0]
                .bids.unshift(value); 
                console.log(state.auctionPosts)
        },
        setChatHistory(state, value){
            // console.log(value);
            state.chatMessages = value;
        },
        setActiveChat(state, value){
            state.activeChat = value;
        },
        sendMessage(state, value){
            state.outgoingMessages.push(value);
        },
        setOutgoingMessages(state, value){
            state.outgoingMessages = [];
        },
        async homeSearchInDb(state, value){
            let posts = null;
            if(value == ""){
                posts = await (await fetch(API_URL + 'auction')).json();
            }/* else if(){
                posts = await (await fetch(API_URL + 'auction/search/' +cat + value)).json();
            }*/else {
                posts = await (await fetch(API_URL + 'auction/search/'+value)).json();
            }
            state.auctionPosts = posts;
        },
        setShowChat(state, value){
            state.showChat = value;
        },
        setNotifications(state, value){
            state.notifications = value;
        },
        addNotification(state, value){
            console.log(value);
            if(state.notifications.filter(n => n.id == value.id).length == 0){
                state.notifications.unshift(value);
            }
        },
        addPush(state, value){
            if(state.pushes.includes(value))
                return;

            if(state.showChat && value.type == 'Message' && value.msgObject.senderid == state.activeChat.id)
                return;

            if(value.type != 'Message' ||
            value.type == 'Message' &&
            value.msgObject.senderid != this.state.me.userid &&
            value.msgObject.hasread == 0){
                state.pushes.push(value);
            }
        }
    },
    actions: {
        async getPostsFromDb() {
            let posts = await (await fetch(API_URL + 'auction')).json();
            this.commit('setPosts', posts);
        },
        async getUser() {
            let user = await (await fetch(API_URL + 'user/profile')).json();
            this.commit('setUser', user);
        },
        async getCategoriesFromDb() {
            let categories = await (await fetch(API_URL + 'category')).json();
            this.commit('setCategories', categories);
        },
        async whoami() {
            let me = await fetch(API_URL + 'user/whoami');
            try{
                me = await me.json();
            }catch(e){
                me = null;
            }
            this.commit('setMe', me);
        },
        async getChatHistory() {
            let history = await fetch(API_URL + 'message');
            try{
                history = await history.json();
            }catch(e){
                history = [];
            }
            this.commit('setChatHistory', history);
        },
        async getNotifications() {
            let history = await fetch(API_URL + 'notification');
            try{
                history = await history.json();
            }catch(e){
                history = [];
            }
            this.commit('setNotifications', history);
        },
        logout(){
            this.commit('setMe', null);
            this.commit('setOutgoingMessages', []);
            this.commit('setActiveChat', null);
            this.commit('setChatHistory', []);
            this.commit('setNotifications', []);
            this.dispatch('connectSocket');
        },
        connectSocket() {
            if(this.state.socket){
                this.state.socket.close();
            }
            this.state.socket = new WebSocket('ws://localhost:8080/websocket');
            this.state.socket.onmessage = (e) => {
                let msg = JSON.parse(e.data);
                // console.log(msg);
                switch (msg.type) {
                    case 'Bids':
                        this.commit('appendBid', msg.msgObject);
                        break;
                    case 'Message':
                        this.commit('addChatMsg', msg.msgObject);
                        this.commit('addPush', msg);
                        break;
                    case 'MessageHistory':
                        this.commit('setChatHistory', msg.msgObject);
                        break;
                    case 'Notification':
                        this.commit('addNotification', msg.msgObject);
                        this.commit('addPush', msg);
                        break;
                    default:
                        console.log("Error in msg", msg);
                        break;
                }
            }
            this.state.socket.onopen = (e) => {
                console.log("Connected");
                this.commit('setSocketConnection', true)
            };
            this.state.socket.onclose = (e) => {
                console.log("Closing websocket...");
                this.commit('setSocketConnection', false);
                this.state.socket = null;
            };
            console.log("Connecting...");
        },
        async readCurrentChat(){
            await fetch(API_URL + 'message/read/'+this.state.activeChat.id);
        },
        removePush(){
            if(this.state.pushes.length > 0){
                // console.log("KILLING PUSH");
                let last = this.state.pushes.shift();
                if(last.type == 'Message'){
                    for(let i = 0; i < this.state.pushes.length; i++){
                        if(this.state.pushes[i].type == 'Message' && this.state.pushes[i].msgObject.senderid == last.msgObject.senderid){
                            // console.log("Removing push that is similar");
                            this.state.pushes.splice(i, 1);
                            i--;
                        }
                    }
                }
            }
        },
        markNotificationsAsRead(){
            for(let i = 0; i < this.state.notifications.length; i++){
                this.state.notifications[i].hasread = 1;
            }
            fetch('/api/notification/read');
        },
        async getOnlineList(){
            let online = await fetch(API_URL + 'user/online');
            try{
                online = await online.json();
            }catch(e){
                online = [];
            }
            this.commit('setOnlineList', online);
        }
    }
})
