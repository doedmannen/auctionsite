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
        user: null,
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
        setUser(state, value) {
            state.user = value;
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
    }
})
