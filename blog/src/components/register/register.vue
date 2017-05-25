<template>
	<div class="register">
		<div class="img_box">
			<img src="../img/login_bg.png" alt="login" title="login">
		</div>
		<form>
			<h1><img src="../img/logo.png" alt="logo" title="logo">注册</h1>
			<div :class="['entry_box', item.isError ? 'error_input' : '']" v-for="item in registerForm">
				<e-tip :text="item.tip.text" :show="item.tip.show" :place="item.tip.place">
					<e-input v-ep-proving="item" v-model="item.value" :name="item.name" :type="item.type" :group="item.group"  :placeholder="item.placeholder" :isError="item.isError" ></e-input>
				</e-tip>
			</div>
			<div :class="['entry_box', item.isError ? 'error_input' : '']" v-for="item in validateCodeForm">
				<div class="validate_input">
					<e-tip :text="item.tip.text" :show="item.tip.show" :place="item.tip.place">
						<e-input v-ep-proving="item" :group="item.group" v-model="item.value" :name="item.name" :type="item.type" :placeholder="item.placeholder" :isError="item.isError" ></e-input>
					</e-tip>
				</div>
				<div class="validate_code" :class="[validateTime > 0 ? 'clicked' : '']" :disabled="validateTime > 0 ? true : false" @click.stop.prevent="getValidateCode">{{getValidateText}}</div>
			</div>
			<div class="button_box">
				<a href="#" @click.stop.prevent="register($event)">注册</a>
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
				registerForm:{
					email: {
						name: 'email',
						alias: '邮箱',
						group: 'registerGroup',
						value: '',
						placeholder: '请输入邮箱',
						rule: [{type: 'nonvoid', typeVal: true}, {type: 'reg', typeVal: 'Mail', errMsg: '请输入正确的邮箱'}, {type: 'ajax', typeVal: this.URL.EXISTEMAIL_URL, errMsg: '邮箱已存在'}],
						isNow: true,
						canNull: false,
						isError: false,
						tip: {
							place: 'right',
							text: '',
							show: false
						}
					},
					nickName: {
						name: 'nickName',
						alias: '用户名',
						value:'',
						placeholder:'请输入用户名',
						group: 'registerGroup',
						rule: [{type: 'nonvoid', typeVal: true}, {type: 'reg', typeVal: /^[\\u4e00-\\u9fa5_a-zA-Z0-9-]{1,16}$/, errMsg: '请输入正确的用户名'}],
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
						type:'password',
						alias: '密码',
						group: 'registerGroup',
						value:'',
						placeholder:'请输入密码',
						rule: [{type: 'nonvoid', typeVal: true}, {type: 'reg', typeVal: /^[A-Za-z0-9@#\\.]{6,16}$/, errMsg: '请输入6-16位密码'}],
						isNow: true,
						canNull: false,
						isError: false,
						tip: {
							place: 'right',
							text: '',
							show: false
						}
					},
					tryPassword: {
						name: 'tryPassword',
						type: 'password',
						group: 'registerGroup',
						value: '',
						placeholder: '请确认密码',
						rule: [{type: 'nonvoid', typeVal: true, errMsg: '请重新输入密码'}, {type: 'equal', typeVal: 'password', errMsg: '两次输入密码不匹配'}],
						isNow: true,
						canNull: false,
						isError: false,
						tip: {
							place: 'right',
							text: '',
							show: false
						}
					}
				},
				validateCodeForm:{
					validateCode: {
						name:'validateCode',
						value:'',
						group: 'validateCodeGroup',
						alias: '验证码',
						placeholder:'请输入验证码',
						rule: [{type: 'nonvoid', typeVal: true, errMsg: '验证码不能为空'}, {type: 'reg', typeVal: /^[A-Za-z0-9@#\\.]{4}$/, errMsg: '请输入4位字符的验证码'}],
						isNow: true,
						canNull: false,
						isError: false,
						tip: {
							place: 'left',
							text: '',
							show: false
						}
					}
				},
				validateTime: 0
			}
		},
		computed: {
			getValidateText () {
				return this.validateTime > 0 ? this.validateTime + 's 后获取' : '获取验证码'
			}
		},
		methods: {
			register (event) {
				let _this = this
				// 如果信息不完整
				let flag = _this.epCheck(['registerGroup'], this.registerForm)
				if (!flag) {
					return
				}
				// 如果验证码未填写
				flag = _this.epCheck(['validateCodeGroup'], this.validateCodeForm)
				if (!flag) {
					return
				}
				// 构造对象
				let registerData = {}
				for (let name in this.registerForm) {
					let obj = this.registerForm[name]
					registerData[obj.name] = obj.value
				}
				registerData['validateCode'] = this.validateCodeForm.validateCode.value
				// 注册
				_this.$http.post(_this.URL.REGISTER_URL, _this.Global.querystring.stringify(registerData)).then((re) => {
					let data = re.data
					if (re.status === 200) {
						if (data.success) {
							// 执行登录
							_this.$store.dispatch('login', data.data)
							// 是否跳转
						} else {
							// 注册失败
							_this.$Notice.error({
			                    title: '注册失败',
			                    duration: 5,
			                    top: 50
			                })
						}
					}
				}).catch((error) => {
					console.log('error' + error)
				})
			},
			getValidateCode () {
				if (this.validateTime > 0) {
					return
				}
				let email = this.registerForm.email.value
				let emailObj = this.registerForm.email
				let _this = this
				let excution = {}
				const EMAIL_HASEMAIL_TEXT = '此邮箱已被注册'
				excution['email'] = email
				console.log(this.epCheckSingle(['registerGroup'], this.registerForm, 'email'))
				if (!this.epCheckSingle(['registerGroup'], this.registerForm, 'email')) {
					return
				}
				// 设置clock
				this.starValidate()
				_this.$http.post(_this.URL.GET_VALIDATECODE, _this.Global.querystring.stringify(excution)).then((re) => {
					if (re.status === 200) {
						let data = re.data
				      	let substance = data.data
				      	// 如果登录成功
				      	if (data.success) {
				      		_this.$Notice.success({
				      			title: '发送邮件成功'
				      		})
				      	} else {
				      		switch (substance.code) {
				      			case _this.ERROR_CODE.EMAIL_ERROR :
				      				_this.$Notice.error({title: '发送邮件失败'})
				      			break
				      			case _this.ERROR_CODE.EMAIL_TIMEERROR :
				      				_this.$Notice.error({title: '发送邮件失败'})
				      			break
				      			case _this.ERROR_CODE.EMAIL_HASEMAIL :
				      				_this.showTipError(emailObj, EMAIL_HASEMAIL_TEXT)
				      			break
				      		}
				      		_this.$Notice.error({
				      			title: '发送邮件失败'
				      		})
				      	}
					}
				}).catch((error) => {
					console.log('error' + error)
				})
			},
			starValidate () {
				this.validateTime = 60
				this.timer()
			},
			timer () {
				if (this.validateTime > 0) {
					this.validateTime --
					setTimeout(this.timer, 1000)
				}
			}
		},
		components:{
			eTip,
			eInput
		}
	}
</script>

<style  lang="stylus">
.register
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
			height 370px
			transform translateY(-50px)
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
				.validate_input
					float left
					width 50%
				.validate_code
					float right
					text-align center
					width 40%
					border-radius 2px
					height 36px
					line-height 36px
					padding 0
					border 1px solid #bbb
					background #fff
					cursor pointer
					color #868686
					font-size 14px
					&.clicked
						cursor not-allowed
						border 1px solid #fff
						&:hover
							border 1px solid #fff
					&:hover
						border 1px solid #08c
			.button_box
				a
					width 100%
					height 38px
					line-height 38px
					float left
					background #67bdcd
					color #fff
					border-radius 3px
					text-align center
					font-size 14px
					border 1px solid #67bdcd
					&:hover
						background #3da4be
						border 1px solid #3da4be
</style>
