import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import baseCard from "@/components/base/BaseCard";
import BaseButton from "@/components/base/BaseButton";

Vue.config.productionTip = false

new Vue({
  baseCard,
  BaseButton,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
