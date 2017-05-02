<template>
	<div :class="outBoxClass ? outBoxClass : ''">
		<input ref="input" :class="[iClass ? iClass : '','e_input', isError === true ? 'e_input_error' : '']" :type="type ? type : 'text'" :name="name ? name : ''" :placeholder="placeholder" v-bind:value="value" v-on:input="updateValue($event.target.value)" @click.stop.prevent="click($event)" @blur.stop.prevent="blur($event)">
	</div>
</template>

<script>
	export default {
		name: 'eInput',
		props: {
			// 名称
			name: {
				type: String
			},
			// 父元素class
			outBoxClass: {
				type: String
			},
			// 类型
			type: {
				type: String
			},
			// 初始化提示
			placeholder: {
				type: String
			},
			// 空字符串提示
			emptyText: {
				type: String
			},
			// 值
			value: {
				type: String
			},
			// 错误提示
			errorText: {
				type: String
			},
			// 校验正则
			verification: {
				type: String
			},
			// 是否错误
			isError: {
				type: Boolean
			},
			// input class
			iClass: {
				type: String
			},
			// input 改变事件
			iChange: {
				type: Function
			},
			// input 点击事件
			iClick: {
				type: Function
			},
			// input blur事件
			iBlur: {
				type: Function
			}
		},
		methods: {
			updateValue (value) {
				let formValue = value.trim()
				if (formValue !== value) {
					this.$refs.input.value = formValue
				}
				this.$emit('input', String(formValue))
				// 绑定change事件
				if (typeof this.iChange === 'function') {
					this.iChange(value, this)
				}
			},
			click (value) {
				if (typeof this.iClick === 'function') {
					this.iClick(value, this)
				}
			},
			blur (value) {
				if (typeof this.iBlur === 'function') {
					this.iBlur(value, this)
				}
			}
		}
	}
</script>

<style  lang="stylus" scoped>
	.e_input
		display block
		width 100%
		height 36px
		padding 6px 7px
		font-size 14px
		line-height 1.5
		border 1px solid #d7dde4
		border-radius 4px
		color #657180
		background #fff
		position relative
		cursor text
		transition border .2s ease-in-out, background .2s ease-in-out, box-shadow .2s ease-in-out
		&.e_input_error
			border-color #ff3300
			&:focus
				outline 0
				box-shadow 0 0 0 2px rgba(255, 51, 0, .2)
			&:focus, &:hover
				border-color #ff3300
		&:focus
			outline 0
			box-shadow 0 0 0 2px rgba(51, 153, 255, .2)
		&:focus, &:hover
			border-color #5cadff
</style>
