import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
const API_URL = "/api/";

export default new Vuex.Store({
  state: {
    auctionPosts: []
  },
  mutations: {
    setPosts(state, value){
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
