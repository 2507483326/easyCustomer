let ErrorCode = (() => {
	// 统一错误码
    const ERROR_CODE = 600

    // 用户未登录状态码
    const USER_NOLOGIN = 601

    // 用户未授权状态码
    const USER_NOAUTHORITY = 602

    // 密码错误
    const USER_PASSWORDERROR = 603

    // 账号不存在
    const USER_NOACCOUNT = 604

    // 账号被锁定
    const USER_LOCKACCOUNT = 605

    // 账号异常登录
    const USER_ABNORMALACOUNT = 606

    // 账号session过期
    const USER_OVERDUEACOUNT = 607

    // 邮件发送未知异常
    const EMAIL_ERROR = 608

    // 邮件发送1分钟时间未到，疑似破解
    const EMAIL_TIMEERROR = 609

    // 邮件已注册
    const EMAIL_HASEMAIL = 610

	return {
		ERROR_CODE,
		USER_NOLOGIN,
		USER_NOAUTHORITY,
		USER_PASSWORDERROR,
		USER_NOACCOUNT,
		USER_LOCKACCOUNT,
		USER_ABNORMALACOUNT,
		USER_OVERDUEACOUNT,
        EMAIL_ERROR,
        EMAIL_TIMEERROR,
        EMAIL_HASEMAIL
	}
})()

export default ErrorCode
