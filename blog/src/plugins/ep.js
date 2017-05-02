var ep = {}


function check (value, conditions) {
	var flag = 0
	//验证函数
    var cfg = {
        //非空
        nonvoid: (v, bool)=>{tt
            if(bool){
                return v.trim() ? 0 : ['nonvoid'];
            }else{
                return 0;
            }
        },
        reg:(v, reg)=> reg.test(v) ? 0 : ['reg'],                //正则
        limit:(v, interval)=> (+v >= interval[0] && +v <= interval[1]) ? 0 : ['limit', interval],
        equal: (v, target)=>{                                                        //和什么相等
            var _list = document.getElementsByName(target), _target
            for(var i = 0;i < _list.length;i++){
                if(_list[i].className.indexOf('va') > -1){
                    _target = _list[i];
                }
            }
            return (_target.value === v) ? 0 : ['equal', _target.getAttribute('tag')]
        },
        unique:(v)=>{
            var _list = document.getElementsByClassName('unique'),
                    valList = [].map.call(_list, item=>item.value)
            return (unique(valList).length === valList.length) ? 0 : ['unique']
        }
    }

    for (var i = 0; i < conditions.length; i++) {
    	var condi = conditions[i],
    		type = condi.type,
    		typeVal = condi.typeVal
    	flag = cfg[type](v, typeVal)
    	if (flag) {
    		res = condi.errMsg
    		break
    	}
    }
    return res
}

ep.install = function (Vue, options) {

	Vue.directive('ep-proving',{
		bind: function (el, binding, vnode) {
			// 初始化变量
			var vm = vnode.context,
				option = binding.modifiers
		}
	})


}