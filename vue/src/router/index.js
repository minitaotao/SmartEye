import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Manager.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import(/* webpackChunkName: "about" */ '../views/Manager.vue'),
    children:[
      {path: 'home', name: 'Home', component: () => import(/* webpackChunkName: "about" */ '../views/Home.vue')},
      {path: 'route1', name: 'Route1', component: () => import(/* webpackChunkName: "about" */ '../views/Route1.vue')},
      {path: 'route2', name: 'Route2', component: () => import(/* webpackChunkName: "about" */ '../views/Route2.vue')},
      {path: 'route3', name: 'Route3', component: () => import(/* webpackChunkName: "about" */ '../views/Route3.vue')},
      {path: 'route0', name: 'Route0', component: () => import(/* webpackChunkName: "about" */ '../views/Route0.vue')},
      {path: 'route4', name: 'Route4', component: () => import(/* webpackChunkName: "about" */ '../views/Route4.vue')},
      {path: 'live', name: 'Live', component: () => import(/* webpackChunkName: "about" */ '../views/live.vue')},
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue'),
  },
  {
    path: '/set',
    name: 'Set',
    component: () => import(/* webpackChunkName: "about" */ '../views/Set.vue'),
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


export default router
