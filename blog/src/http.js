import axios from 'axios'
import Global from './global'

// axios配置
axios.defaults.baseURL = Global.SERVER
axios.defaults.timeout = 10000
// 允许请求设置cookie
axios.defaults.withCredentials = true

// http request 拦截器
axios.interceptors.request.use(config => {
	console.log('axios 发起request 请求')
	return config
}, err => {
	console.log(err)
	return Promise.reject(err)
})

// http response 拦截器
axios.interceptors.response.use(response => {
	console.log('axios 收到服务器回复')
	return response
}, err => {
	if (err.response) {
		switch (err.response.status) {
			case 601:
				console.log('axios 收到服务器601错误')
				break
			case 602:
				console.log('axios 收到服务器602错误')
				break
		}
	}
	return Promise.reject(err)
})

export default axios
