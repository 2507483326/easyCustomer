<template>
	<div class="register">
		<div class="img_box">
			<img src="../img/login_bg.png" alt="login" title="login">
		</div>
		<form>
			<h1><img src="../img/logo.png" alt="logo" title="logo">注册</h1>
			<div class="entry_box" v-for="item in registerForm">
				<e-tip :text="item.tip.text" :show="item.tip.show" :place="item.tip.place">
					<e-input v-model="item.value" :name="item.name" :type="item.type" :placeholder="item.placeholder" :verification="item.verification" :isError="item.isError" :errorText="item.errorText" :emptyText="item.emptyText" :iChange="iChange"></e-input>
				</e-tip>
			</div>
			<div class="entry_box">
				<div class="validate_input">
					<e-tip :text="validateCode.tip.text" :show="validateCode.tip.show" :place="validateCode.tip.place">
						<e-input v-ep-proving:register.Password v-model="validateCode.value" :name="validateCode.name" :type="validateCode.type" :placeholder="validateCode.placeholder" :verification="validateCode.verification" :isError="validateCode.isError" :errorText="validateCode.errorText" :emptyText="validateCode.emptyText" :iChange="iChange"></e-input>
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
					loginName:{
						name:'loginName',
						value:'',
						placeholder:'请输入邮箱',
						verification: '^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$',
						emptyText:'邮箱不能为空',
						errorText:'请输入正确的邮箱',
						isError: false,
						tip: {
							place: 'right',
							text: '',
							show: false
						}
					},
					nickName:{
						name:'nickName',
						value:'',
						placeholder:'请输入用户名',
						verification: '^[\\u4e00-\\u9fa5_a-zA-Z0-9-]{1,16}$',
						emptyText:'用户名不能为空',
						errorText:'请输入正确的用户名',
						isError: false,
						tip: {
							place: 'right',
							text: '',
							show: false
						}
					},
					password:{
						name:'password',
						type:'password',
						value:'',
						placeholder:'请输入密码',
						verification: '^[A-Za-z0-9@#\\.]{6,16}$',
						emptyText:'密码不能为空',
						errorText:'请输入6-16位密码',
						isError: false,
						tip: {
							place: 'right',
							text: '',
							show: false
						}
					},
					tryPassword:{
						name:'tryPassword',
						value:'',
						placeholder:'请确认密码',
						verification: '^[A-Za-z0-9@#\\.]{6,16}$',
						emptyText:'密码不能为空',
						errorText:'输入的密码不匹配',
						isError: false,
						tip: {
							place: 'right',
							text: '',
							show: false
						}
					}
				},
				validateCode: {
					name:'validateCode',
					value:'',
					placeholder:'请输入验证码',
					verification: '^[0-9]{4}$',
					emptyText:'验证码不能为空',
					errorText:'输入正确的验证码',
					isError: false,
					tip: {
						place: 'left',
						text: '',
						show: false
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
				let flag = false
				let register = {}
				// let _this = this
				for (let name in this.registerForm) {
					let obj = this.registerForm[name]
					flag = this.isCheck(obj.value, obj)
					if (!flag) {
						return
					}
				}
				if (!flag) {
					return
				}
				// 验证码单独判断
				if (!this.isCheck(this.validateCode.value, this.validateCode)) {
					return
				}
				for (let name in this.registerForm) {
					let obj = this.registerForm[name]
					register[obj.name] = obj.value
				}
				register['validateCode'] = this.validateCode.value
			},
			iChange (value, _this) {
				let name = _this.name
				console.log(name)
				if (name === 'validateCode') {
					let now = this.validateCode
					this.isCheck(value, now)
				} else {
					let now = this.registerForm[name]
					this.isCheck(value, now)
				}
			},
			isCheck (value, now) {
				let flag = true
				if (value === '' || value.length === 0) {
					if (!(typeof (now.emptyText) === 'undefined')) {
						this.showTipError(now, now.emptyText)
					} else {
						this.showTipError(now, now.errorText)
					}
					flag = false
				} else {
					this.closeTipError(now)
					flag = true
					if (typeof (now.verification) !== 'undefined') {
						let reg = new RegExp(now.verification)
						if (!reg.test(value)) {
							this.showTipError(now, now.errorText)
							flag = false
						} else {
							this.closeTipError(now)
							flag = true
							// 其他 验证
						}
					}
				}
				return flag
			},
			showTipError (obj, text) {
				obj.tip.text = text
				obj.tip.show = true
				obj.isError = true
			},
			closeTipError (obj) {
				obj.tip.show = false
				obj.isError = false
			},
			getValidateCode () {
				if (this.validateTime > 0) {
					return
				}
				let email = this.registerForm.loginName.value
				let emailObj = this.registerForm.loginName
				let _this = this
				let excution = {}
				const EMAIL_HASEMAIL_TEXT = '此邮箱已被注册'
				excution['email'] = email
				console.log(this.registerForm.loginName)
				if (!this.isCheck(email, this.registerForm.loginName)) {
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
