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
        socketConnected: false
    },
    mutations: {
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
            state.chatMessages.push(value);
        },
        setSocket(state, value){
            state.socketConnected = value;
        },
        appendBid(state, value){
            state.auctionPosts
                .filter(ap => value.auctionid == ap.auctionid)[0]
                .bids.unshift(value);
            console.log(state.auctionPosts);
        }
    },
    actions: {
        async getPostsFromDb() {
            let posts = await (await fetch(API_URL + 'auction')).json();
            this.commit('setPosts', posts);
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
    }
})
