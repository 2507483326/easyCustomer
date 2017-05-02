// 存入节点方法
export function saveToLocal (name, value) {
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

//去出节点方法
export function loadFromLocal (name, def) {
	let _root_ = window.localStorage._blog_
	if (!_root_) {
		return def
	}
	_root_ = JSON.parse(_root_)[name]
	if (!_root_) {
		return def
	}
	return ret || def
}
