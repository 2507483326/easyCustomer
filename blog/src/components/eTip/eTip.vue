<!-- 
	组件说明  py Epat 2017-04-18

-->
<template>
	<div class="e_tip_box">
		<!-- 内容 -->
		<slot></slot>
		<!-- e_tip -->
		<transition :name="place">
			<div class="e_tip" :class="getPlace" v-show="show">{{text}}</div>
		</transition>
	</div>
</template>

<script>
	export default {
		props:{
			text: {
				type: String
			},
			show: {
				type: Boolean
			},
			place: {
				type: String
			}
		},
		computed: {
			getPlace () {
				return 'e_tip_' + this.place
			}
		}
	}
</script>

<style  lang="stylus" scoped>
	.e_tip_box
		position relative
		.e_tip
			position absolute
			background #fff
			box-shadow 0 1px 6px rgba(0, 0, 0, .2)
			border-radius 4px
			font-size 12px
			color #848d99
			opacity 1
			word-break keep-all;         
			white-space nowrap;       
			box-sizing border-box
			border-color rgba(0, 0, 0, .2)
			/*三角*/
			&:before
				content ""
				position absolute
				border-width 1px
				border-style solid
				border-color transparent
				width 10px
				height 10px
				background inherit
				opacity 1
			/*right*/
			&.e_tip_right
				top 50%
				left 100%
				padding 8px 16px
				transform translate3d(10px, -50%, 0)
				&:before
					top 50%
					left 0
					border-left-color inherit
					border-bottom-color inherit
					transform translate3d(-50%, -50%, 0) rotate(45deg)
			&.right-enter-active, &.right-leave-active
				transition all .2s linear
			&.right-enter, &.right-leave-active
				opacity 0
				transform translate3d(20%, -50%, 0)
				&:before
					opacity 0
			/*left*/
			&.e_tip_left
				top 50%
				right 100%
				padding 8px 16px
				transform translate3d(-10px, -50%, 0)
				&:before
					top 50%
					right 0
					border-right-color inherit
					border-top-color inherit
					transform translate3d(50%, -50%, 0) rotate(45deg)
			&.left-enter-active, &.left-leave-active
				transition all .2s linear
			&.left-enter, &.left-leave-active
				opacity 0
				transform translate3d(-20%, -50%, 0)
				&:before
					opacity 0
</style>
