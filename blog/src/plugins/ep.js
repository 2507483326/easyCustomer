/*
    表单验证插件
*/
var ep = {}
import axiosHttp from '../http'
import querystring from 'querystring'

function unique (arr) {
    var hashTable = {}
    var newArr = []
    for (var i = 0; i < arr.length; i++) {
        if (!hashTable[arr[i]]) {
            hashTable[arr[i]] = true
            newArr.push(arr[i])
        }
    }
    return newArr
}

// 统一正则表
var regList = {
    ImgCode: /^[0-9a-zA-Z]{4}$/,
    SmsCode: /^\d{4}$/,
    MailCode: /^\d{4}$/,
    UserName: /^[\w|\d]{4,16}$/,
    Password: /^[\w!@#$%^&*.]{6,16}$/,
    Mobile: /^1[3|5|8]\d{9}$/,
    RealName: /^[\u4e00-\u9fa5 ]{2,10}$/,
    BankNum: /^\d{10,19}$/,
    Money: /^([1-9]\d*|0)$/,
    Answer: /^\S+$/,
    Mail: /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/
}

// 验证函数
function check (v, obj) {
	var flag = 0
    let conditions = obj.rule
	// 验证函数
    var cfg = {
        // 非空
        nonvoid: (v, bool) => {
            if (bool) {
                return v.trim() ? 0 : ['nonvoid']
            } else {
                return 0
            }
        },
        reg: (v, reg) => {
            if (regList[reg]) {
                reg = regList[reg]
            }
            return reg.test(v) ? 0 : ['reg']
        },                // 正则
        limit: (v, interval) => (+v >= interval[0] && +v <= interval[1]) ? 0 : ['limit', interval],
        equal: (v, target) => {
            var _list = document.getElementsByName(target)
            var _target
            for (var i = 0; i < _list.length; i++) {
                if (_list[i].className.indexOf('epVa_') > -1) {
                    _target = _list[i]
                }
            }
            return (_target.value === v) ? 0 : ['equal', _target.getAttribute('tag')]
        },
        unique: (v) => {
            var _list = document.getElementsByClassName('unique')
            var valList = [].map.call(_list, item => item.value)
            return (unique(valList).length === valList.length) ? 0 : ['unique']
        },
        // 其他验证
        ajax: (v, url, obj, errMsg) => {
            let excution = {
                data: v
            }
            let tip = obj.tip
            axiosHttp.post(url, querystring.stringify(excution)).then((re) => {
                let data = re.data
                if (re.status === 200) {
                    if (data.success) {
                        if (data.data) {
                            tip.text = errMsg
                            tip.show = true
                        } else {
                            tip.show = false
                        }
                    } else {
                        tip.text = data.data
                        tip.show = true
                    }
                }
            })
            return 0
        }
    }

    for (var i = 0; i < conditions.length; i++) {
    	var condi = conditions[i]
    	var	type = condi.type
    	var	typeVal = condi.typeVal
    	flag = cfg[type](v, typeVal, obj, condi.errMsg)
        // 如果失败了
    	if (flag) {
    		flag = condi.errMsg || flag
    		break
    	}
    }
    return flag
}

// 校验
function verify (value, initArg) {
        // 如果允许为空
        if (value === '' && initArg.canNull) {
            return true
        }
        let result = check(value, initArg)
        if (result === 'ajax') {
            return true
        }
        if (result) {
            // 调用报错方法
            showError(initArg.alias, result, initArg.tip, true)
            initArg['isError'] = true
            return false
        } else {
            // 解除报错方法
            showError(initArg.alias, result, initArg.tip, false)
            initArg['isError'] = false
            return true
        }
    }

// err 方法
function getErrorMessage (name, checkResult) {
    let type = checkResult[0]
    let ext = checkResult[1] || []

    var ERR_MSG = {
        nonvoid: `${name}不能为空`,
        reg: `${name}格式错误`,
        limit: `${name}必须在${ext[0]}与${ext[1]}之间`,
        equal: `两次${ext}不相同`,
        unique: `${name}重复`
    }
    // 正式报错
    return ERR_MSG[type] || checkResult
}

function showError (name, checkResult, tip, isShow) {
    tip.show = isShow
    if (isShow) {
        tip.text = getErrorMessage(name, checkResult)
    }
}

ep.install = (Vue, options) => {
    Vue.directive('ep-proving', {
        bind: (el, binding, vnode) => {
            let initArg = binding.value
            el.setAttribute('name', initArg.name)
            el.name = initArg.name
            // 合并对象
            if (initArg.isNow) {
                el.addEventListener('input', () => {
                    // 如果为空， 且允许为空
                    let value = el.value
                    if (!verify(value, initArg)) {
                        return false
                    }
                })
            }
        }
    })

    // group 数组， el value通过数组获取
    Vue.prototype.epCheck = function (group, data) {
        for (var i = 0; i < group.length; i++) {
            let domList = document.getElementsByClassName('epVa_' + group[i])
            for (var j = 0; j < domList.length; j++) {
                let value = domList[j].value
                let initArg = data[domList[j].getAttribute('name')]
                if (!verify(value, initArg)) {
                    return false
                }
            }
        }
        return true
    }

    // group 数组， el value通过数组获取
    Vue.prototype.epCheckSingle = function (group, data, name) {
        for (var i = 0; i < group.length; i++) {
            let domList = document.getElementsByClassName('epVa_' + group[i])
            for (var j = 0; j < domList.length; j++) {
                if (domList[j].getAttribute('name') === name) {
                    let value = domList[j].value
                    let initArg = data[domList[j].getAttribute('name')]
                    if (!verify(value, initArg)) {
                        return false
                    }
                }
            }
        }
        return true
    }
}

export default ep
