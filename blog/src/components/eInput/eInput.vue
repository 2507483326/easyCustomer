<template>
	<input class="e_input" :class="[isError === true ? 'e_input_error' : '', getGroupNmae]" :type="type ? type : 'text'" :placeholder="placeholder" v-bind:value="value" v-on:input="updateValue($event.target.value)" >
</template>

<script>
	export default {
		name: 'eInput',
		props: {
			// 类型
			type: {
				type: String
			},
			// 值
			value: {
				type: String
			},
			// 是否错误
			isError: {
				type: Boolean
			},
			// 初始化提示
			placeholder: {
				type: String
			},
			group: {
				type: String,
				default: 'default'
			}
		},
		computed: {
			getGroupNmae () {
				return 'epVa_' + this.group
			}
		},
		methods: {
			updateValue (value) {
				let formValue = value.trim()
				if (formValue !== value) {
					this.$refs.input.value = formValue
				}
				this.$emit('input', String(formValue))
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
