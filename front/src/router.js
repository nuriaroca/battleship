import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [{
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/games',
      name: 'games',
      component: () => import('./views/Games.vue')
    },
    {
      path: "/game/:gamePlayerID",
      props: true,
      name: "game",
      component: () => import('./views/Game.vue')
    },
  ]
})