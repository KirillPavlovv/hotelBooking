import Vue from 'vue'
import VueRouter from 'vue-router'
import RoomsView from '../views/RoomsView.vue'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: import.meta.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: RoomsView
    },

  ]
})

export default router
