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
			// 初始化变量
			var vm = vnode.context
			var	option = binding.modifiers
            var expression = binding.expression
            var group = binding.arg
            var name = el.getAttribute('name')
            console.log(binding.value)
            // 初始化 epList 盒子
            var epList = vm.epList || (vm.epList = {})

            // 初始化组
            var epGroup = epList[group] || (epList[group] = {})

            // 立即校验
            if (option.now) {
                el.addEventListener('change', function () {
                    var flag = 0
                    var value = el.value
                    vm.epResult || (vm.epResult = {})
                    // 如果为空， 且允许为空
                    if (value === '' && option.null) {
                        return
                    }
                    // 校验
                    if (expression) {
                        flag = check(value, expression)
                    }
                    // ajax 验证

                    epGroup[name] = flag
                })
            }
		}
	})
}

module.exports = ep
