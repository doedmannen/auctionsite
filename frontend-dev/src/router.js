import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  base: '/',
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('./views/Home.vue')
    },
    {
      path: '/category/:cat?',
      name: 'cat',
      component: () => import('./views/Home.vue')
    },
    {
      path: '/createauction',
      name: 'CreateAuction',
      component: () => import('./views/CreateAuction.vue')
    },
    {
      path: '/auction/:auctionid',
      name: 'auction',
      component: () => import('./views/SingleAuction.vue')
    },
    {
      path:'/profile/:userid',
      name: 'ProfilePage',
      component: () => import ('./views/ProfilePage.vue')
    }
  ]
})
