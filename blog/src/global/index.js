import querystring from 'querystring'
import ERROR_CODE from './errorCode'
import URL from './url'

let Global = (() => {
	// 定义常量
	const NGINX = 'http://localhost/articleResource/'
	const SERVER = 'http://127.0.0.1:8264/'

	// 用户未登录状态码
    const USER_NOLOGIN = 601

    // 用户未授权状态码
    const USER_NOAUTHORITY = 602

	// 定义方法
	// 存入节点方法
	const saveToLocal = (name, value) => {
		let _root_ = window.localStorage._blog_
		if (!_root_) {
			_root_ = {}
		} else {
			_root_ = JSON.parse(_root_)
			if (!_root_) {
				_root_ = {}
			}
		}
		_root_[name] = value
		window.localStorage._blog_ = JSON.stringify(_root_)
	}

	// 取出节点方法 默认值
	const loadFromLocal = (name, def) => {
		let _root_ = window.localStorage._blog_
		if (!_root_) {
			return def || null
		}
		_root_ = JSON.parse(_root_)[name]
		if (!_root_) {
			return def || null
		}
		return _root_ || def || null
	}

	return {
		NGINX,
		SERVER,
		USER_NOLOGIN,
		USER_NOAUTHORITY,
		saveToLocal,
		loadFromLocal,
		querystring,
		ERROR_CODE,
		URL
	}
})()

export default Global
