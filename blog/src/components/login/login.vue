<template>
	<div class="login">
		<div class="img_box">
			<img src="../img/login_bg.png" alt="login" title="login">
		</div>
		<form>
			<h1><img src="../img/logo.png" alt="logo" title="logo">登录</h1>
			<div :class="['entry_box', item.isError ? 'error_input' : '']" v-for="item in loginForm">
				<e-tip :text="item.tip.text" :show="item.tip.show" :place="item.tip.place">
					<e-input v-ep-proving="item" v-model="item.value" :name="item.name" :type="item.type" :group="item.group" :placeholder="item.placeholder" :isError="item.isError" ></e-input>
				</e-tip>
			</div>
			<div class="button_box">
				<a href="#" @click.stop.prevent="login">登录</a>
				<router-link to="register">注册</router-link>
			</div>
		</form>
	</div>
</template>

<script>
	import eTip from 'components/eTip/eTip'
	import eInput from 'components/eInput/eInput'

	export default {
		data () {
			return {
				loginForm:{
					loginName: {
						name: 'loginName',
						alias: '邮箱',
						value: '',
						placeholder: '请输入邮箱',
						group: 'login',
						rule: [{type: 'nonvoid', typeVal: true}, {type: 'reg', typeVal: 'Mail', errMsg: '请输入正确的邮箱'}],
						isNow: true,
						canNull: false,
						isError: false,
						tip: {
							place: 'right',
							text: '',
							show: false
						}
					},
					password: {
						name: 'password',
						alias: '密码',
						type:'password',
						placeholder:'请输入密码',
						value:'',
						group: 'login',
						rule: [{type: 'nonvoid', typeVal: true}, {type: 'reg', typeVal: 'Password', errMsg: '请输入6-16位字符的密码'}],
						isNow: true,
						canNull: false,
						isError: false,
						tip: {
							place: 'right',
							text: '',
							show: false
						}
					}
				}
			}
		},
		methods:{
			showTipError (obj, text) {
				obj.tip.text = text
				obj.tip.show = true
				obj.isError = true
			},
			closeTipError (obj) {
				obj.tip.show = false
				obj.isError = false
			},
			login () {
				let _this = this
				let user = {}
				let flag = _this.epCheck(['login'], this.loginForm)
				if (!flag) {
					return
				}
				for (let name in this.loginForm) {
					let obj = this.loginForm[name]
					user[obj.name] = obj.value
				}
				// 退出登录
				_this.$http.post(_this.URL.LOGIN_OUT).then((re) => {
					if (re.status === 200) {
						// 登录
						_this.$http.post(_this.URL.LOGIN_URL, _this.Global.querystring.stringify(user)).then((re) => {
					      if (re.status === 200) {
					      	let data = re.data
					      	let substance = data.data
					      	// 如果登录成功
					      	if (data.success) {
					      		_this.loginSuccess(substance)
					      	} else {
					      		_this.loginError(substance)
					      	}
					      }
					    })
					}
				}).catch(() => {
					_this.showTip('登录失败，请重试')
				})
			},
			showTip (text) {
				this.$Notice.error({
                    title: text,
                    duration: 5,
                    top: 50
                })
			},
			loginSuccess (substance) {
				if (substance) {
	      			this.$store.dispatch('login', substance)
	      		}
			},
			loginError (substance) {
				let loginName = this.loginForm.loginName
				let loginPassword = this.loginForm.password
				const USER_PASSWORDERROR_TEXT = '用户名密码不匹配'
				const USER_NOACCOUNT_TEXT = '用户名不存在'
				const USER_LOCKACCOUNT_TEXT = '账号被锁定'
				const USER_ABNORMALACOUNT_TEXT = '账号异常'
				const USER_OVERDUEACOUNT_TEXT = '账号session过期'
				const ERROR_TEXT = '登录异常，重新登录'
				switch (substance.code) {
					// 如果是用户密码错误
					case this.ERROR_CODE.USER_PASSWORDERROR :
						this.showTipError(loginPassword, USER_PASSWORDERROR_TEXT)
					break
					// 如果是用户账号不存在
					case this.ERROR_CODE.USER_NOACCOUNT :
		                this.showTipError(loginName, USER_NOACCOUNT_TEXT)
					break
					// 如果是用户账号被锁定
					case this.ERROR_CODE.USER_LOCKACCOUNT :
		                this.showTipError(loginName, USER_LOCKACCOUNT_TEXT)
					break
					// 如果是用户账号异常登录
					case this.ERROR_CODE.USER_ABNORMALACOUNT :
		                this.showTipError(loginName, USER_ABNORMALACOUNT_TEXT)
					break
					// 如果是用户账号session过期重新登录 ？ 存疑
					case this.ERROR_CODE.USER_OVERDUEACOUNT :
						this.showTip(USER_OVERDUEACOUNT_TEXT)
					break
					// 其他错误
					case this.ERROR_CODE.ERROR_CODE :
						this.showTipError(loginName, ERROR_TEXT)
		                this.showTip(ERROR_TEXT)
					break
					default :
		                this.showTip('登录失败，请重试')
				}
			}
		},
		components: {
			eTip,
			eInput
		}
	}
</script>

<style  lang="stylus" scoped>
	.login
		text-align center
		height 300px
		position absolute
		top 0
		bottom 0
		left 0
		right 0
		margin  auto
		.img_box
			width 400px
			height 300px
			display inline-block
			vertical-align top
			margin-right 100px
			img
				display block
				width 100%
				height 100%
		form
			display inline-block
			width 300px
			height 200px
			h1
				font-size 20px
				height 24px
				line-height 24px
				color #2d2d2e
				text-align center
				margin-bottom 18px
				img
					display inline-block
					height 24px
					vertical-align top
					margin-right 10px
			.entry_box
				height 40px
				margin-bottom 18px
				width 100%
				position relative
				&.error_input
					input
						border-color #ff6600
			.button_box
				a
					width 40%
					height 38px
					line-height 38px
					float left
					background #43b3cf
					color #fff
					text-align center
					font-size 14px
					border 1px solid #43b3cf
					&:hover
						background #3da4be
						border 1px solid #3da4be
					&:last-child
						float right
						background #fff
						border 1px solid #bbb
						color #666
						&:hover
							background #f2f2f2
			/*.	注册
				validate_code
				input
					width 60%
					float left
				a
					float right
					width 35%
					height 40px
					background #43b3cf
					text-align center
					color #fff
					font-size 14px
					line-height 40px
					&:hover
						background #3da7c1
						text-decoration none*/
					
</style>
