import Vue from 'vue'
import VueRouter from 'vue-router'
import App from '../App.vue'
import index from 'components/index/index'
import login from 'components/login/login'
import register from 'components/register/register'
import aboutMe from 'components/aboutMe/aboutMe'
import manager from 'components/manager/manager'

Vue.use(VueRouter)

const routes = [
    {
    	path: '/',
    	component: App,
    	children:[
    		{path:'', redirect:'index'},
    		{path:'index', component:index},
    		{path:'login', component:login},
    		{path:'register', component:register},
    		{path:'aboutMe', component:aboutMe},
    		{path:'manager', component:manager}
    	]
    }
]

export default new VueRouter({
  linkActiveClass:'active',
  mode:'history',
  routes
})
