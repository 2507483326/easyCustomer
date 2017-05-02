// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import router from './router'
import 'common/stylus/base.styl'
import Global from './global'
import URL from './global/url'
import ERROR_CODE from './global/errorCode'
import http from './http'
// 启用iview ui组件库
import iView from 'iview'
import 'iview/dist/styles/iview.css'
Vue.use(iView)
Vue.prototype.Global = Global
Vue.prototype.URL = URL
Vue.prototype.ERROR_CODE = ERROR_CODE
Vue.prototype.$http = http

// vue-resource跨域
// Vue.http.options.xhr = { withCredentials: true }
// Vue.http.options.emulateJSON = true

// 4. 创建和挂载根实例。
// 记得要通过 router 配置参数注入路由，
// 从而让整个应用都有路由功能
/* eslint-disable no-unused-vars */
const app = new Vue({
  router
}).$mount('#app')
