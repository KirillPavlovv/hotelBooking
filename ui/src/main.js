import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import baseCard from "@/components/base/BaseCard"
import BaseButton from "@/components/base/BaseButton"
import './assets/theme.css'
import {initFormatters} from '@/components/formatters'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false
initFormatters(Vue)

new Vue({
  BootstrapVue,
  IconsPlugin,
  baseCard,
  BaseButton,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
