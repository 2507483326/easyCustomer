<!-- 
	组件说明  py Epat 2017-06-01

-->
<template>
	<div>
		<Menu mode="horizontal" :active-name="activeName">
			<Menu-item name="1">
				<Icon type="ios-paper"></Icon>
				文章管理
			</Menu-item>
			<Menu-item name="2">
				<Icon type="ios-people"></Icon>
				用户管理
			</Menu-item>
		</Menu>
		<p>{{activeName}}</p>
	</div>
</template>

<script>
	export default {
		data () {
			return {
				activeName: 1
			}
		},
		created () {
			this.$on('on-select', (v) => {
				console.log(v)
			})
		}
	}
</script>

<style  lang="stylus" scoped>
</style>
