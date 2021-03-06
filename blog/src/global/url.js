let URL = (() => {
    // 登录
    const LOGIN_URL = 'user/login'

    // 退出登录
    const LOGIN_OUT = 'user/loginOut'

    // 记住我登录
    const REMEMBER_LOGIN = 'user/rememberLogin'

    // 获取验证码
    const GET_VALIDATECODE = 'user/getValidateCode'

    // 注册
    const REGISTER_URL = 'user/register'

    // 判断邮箱是否存在
    const EXISTEMAIL_URL = 'user/isExistEmail'

	return {
        LOGIN_URL,
        LOGIN_OUT,
        REMEMBER_LOGIN,
        GET_VALIDATECODE,
        REGISTER_URL,
        EXISTEMAIL_URL
	}
})()

export default URL
