// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueBus from 'vue-bus'
import store from './router/store'
Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.use(VueAxios, axios);
Vue.use(VueBus);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  
  components: { App },
  template: '<App/>'
})
