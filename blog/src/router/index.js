import Vue from 'vue'
import VueRouter from 'vue-router'
import App from '../App.vue'
import index from 'components/index/index'
import login from 'components/login/login'
import register from 'components/register/register'
import aboutMe from 'components/aboutMe/aboutMe'

Vue.use(VueRouter)

const routes = [
    {path: '/', component: App, children:[{path:'', redirect:'index'}, {path:'index', component:index}, {path:'login', component:login}, {path:'register', component:register}, {path:'aboutMe', component:aboutMe}]}
]

export default new VueRouter({
  linkActiveClass:'active',
  mode:'history',
  routes
})
