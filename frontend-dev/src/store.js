import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
const API_URL = "/api/";

export default new Vuex.Store({
    state: {
        auctionPosts: [],
        auctionCategories: []
    },
    mutations: {
        setPosts(state, value) {
            state.auctionPosts = value
        },
        setCategories(state, value) {
            state.auctionCategories = value
        }
    },
    actions: {
        async getPostsFromDb() {
            let posts = await (await fetch(API_URL + 'auction/')).json();
            this.commit('setPosts', posts);
        },
        async getCategoriesFromDb() {
            let categories = await (await fetch(API_URL)).json();
            this.commit('setCategories', categories);
        }
    }
})
