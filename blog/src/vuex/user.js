const user = {
	state:{
		flag:false,
		user:{
			type:Object
		}
	},
	mutations:{
		setLogin (state, amount) {
			state.flag = amount
		},
		setUser (state, amount) {
			state.user = amount
		}
	},
	actions:{
		login ({ commit, state }, data) {
			commit('setUser', data)
			commit('setLogin', true)
		},
		loginOut ({ commit, state }) {
			commit('setLogin', false)
			commit('setUser', {type:Object})
		}
	},
	getters:{
		isLogin: state => {
			return state.flag
		},
		getUser: state => {
			return state.user
		}
	}
}
export default user
