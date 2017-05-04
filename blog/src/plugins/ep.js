/*
    表单验证插件
*/
var ep = {}

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

// 验证函数
function check (v, conditions) {
	var flag = 0
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
        reg: (v, reg) => reg.test(v) ? 0 : ['reg'],                // 正则
        limit: (v, interval) => (+v >= interval[0] && +v <= interval[1]) ? 0 : ['limit', interval],
        equal: (v, target) => {                                                        // 和什么相等
            var _list = document.getElementsByName(target)
            var _target
            for (var i = 0; i < _list.length; i++) {
                if (_list[i].className.indexOf('va') > -1) {
                    _target = _list[i]
                }
            }
            return (_target.value === v) ? 0 : ['equal', _target.getAttribute('tag')]
        },
        unique: (v) => {
            var _list = document.getElementsByClassName('unique')
            var valList = [].map.call(_list, item => item.value)
            return (unique(valList).length === valList.length) ? 0 : ['unique']
        }
    }

    for (var i = 0; i < conditions.length; i++) {
    	var condi = conditions[i]
    	var	type = condi.type
    	var	typeVal = condi.typeVal
    	flag = cfg[type](v, typeVal)
    	if (flag) {
    		flag = condi.errMsg
    		break
    	}
    }
    return flag
}

ep.install = function (Vue, options) {
	Vue.directive('ep-proving', {
		bind: function (el, binding, vnode) {
            var flag = false
            if (flag) {
                check(1, 2)
            }
			el.addEventListener('input', function () {
            })
		}
	})
}

module.exports = ep
