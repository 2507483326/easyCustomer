<template>
  <div id="app">
  	<headBar></headBar>
  	<loding :isLoding="isLoding"></loding>
  	<div class="app_wrapper">
  		<div class="app_inner_box">
  			<router-view></router-view>
  		</div>
  	</div>
  	<foot></foot>
  </div>
</template>

<script>
import loding from 'components/loding/loding'
import foot from 'components/foot/foot'
import headBar from 'components/headBar/headBar'
import store from './vuex/store'
export default {
	data () {
		return {
			lodingFlag:{
		    	type:Boolean
		    }
		}
	},
	watch:{
		isLoding () {
			this.lodingFlag = this.$store.getters.isLoding
		}
	},
	computed:{
		isLoding () {
			return this.$store.getters.isLoding
		}
	},
	mounted () {
		console.log('载入')
		// 利用remember进行登录
		let _this = this
		console.log(this)
		// 退出登录
		_this.$http.post(_this.URL.REMEMBER_LOGIN).then((re) => {
			if (re.status === 200) {
				console.log(re.data)
				let data = re.data
		      	let substance = data.data
		      	// 如果登录成功
		      	if (data.success) {
		      		if (substance) {
		      			_this.$store.dispatch('login', substance)
		      		}
		      	} else {
		      		_this.rememberError(substance)
		      	}
			}
			_this.$store.dispatch('lodingEnd')
		}).catch(() => {
			_this.$store.dispatch('lodingEnd')
		})
	},
	methods:{
		rememberError (substance) {
			switch (substance.code) {
				// 如果是用户未登录
				case this.ERROR_CODE.USER_NOLOGIN : break
				// 如果是用户账号不存在
				case this.ERROR_CODE.USER_NOACCOUNT : break
				// 其他错误
				case this.ERROR_CODE.ERROR_CODE : break
			}
		}
	},
	components:{
		loding,
		foot,
		headBar
	},
	store
}
</script>

<style  lang="stylus" scoped>
	#app
		width 100%
		height calc(100vh - 50px)
		padding-top 50px
		position relative
		overflow auto
		font-size 0
		background #f2f2e9
		.app_wrapper
			min-height 100%
			height 100%
			width 100%
			display inline-block
			position relative
			&:after
				display block
				content ""
				height 0
				line-height 0
				clear both
				visibility hidden
			.app_inner_box
				padding-bottom 50px
				position relative
				box-sizing border-box
				min-height 100%
</style>
