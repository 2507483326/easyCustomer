const app = {
	state:{
		// 判断页面是否处于加载状态
		isLoding:true
	},
	mutations:{
		setLoding (state, amount) {
			state.isLoding = amount
		}
	},
	actions:{
		loding ({ commit, state }) {
			commit('setLoding', true)
		},
		lodingEnd ({ commit, state }) {
			commit('setLoding', false)
		}
	},
	getters:{
		isLoding: state => {
			return state.isLoding
		}
	}
}
export default app
