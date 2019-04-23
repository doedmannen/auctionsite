import Vue from 'vue'
import Router from 'vue-router'

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
      path: '/about',
      name: 'about',
      component: () => import('./views/About.vue')
    },
    {
      path: '/user/login',
      name: 'user',
      component: () => import('./views/Home.vue')
    },
    {
      path: '/createauction',
      name: 'CreateAuction',
      component: () => import('./views/CreateAuction.vue')
    }
  ]
})
