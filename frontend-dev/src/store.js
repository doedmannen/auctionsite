import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
const API_URL = "/api/";

export default new Vuex.Store({

    state: {
        currentUploads: [],
        auctionPosts: []
    },
    mutations: {
        addUpload(state, value) {
            state.currentUploads.push(value);
        },
        clearUploads(state, value) {
            state.currentUploads = [];
        },
        setPosts(state, value) {
            state.auctionPosts = value
        }

    },
    actions: {
        async getPostsFromDb() {
            let posts = await (await fetch(API_URL + 'auction/')).json();
            this.commit('setPosts', posts);
        }
    }
})
