<template>
	<transition name="loding">
		<div class="loding" v-if="isLoding">
			<div class="loding_inner">
				<img src="./img/load.png" height="232" width="149">
				<div class="text">Loading  ...</div>
			</div>
		</div>
	</transition>
</template>

<script>
	export default {
		props:{
			isLoding:{
				type:Boolean
			}
		}
	}
</script>

<style  lang="stylus" scoped>
	.loding
		position fixed
		z-index 9999
		top 0
		left 0
		width 100%
		height 100%
		opacity 1
		transition all 1s linear
		&.loding-enter, &.loding-leave-active
			opacity 0
		.loding_inner
			width 100%
			height 100%
			background #95d5dc
			animation load-background-animate 40s infinite
			img
				height 100px
				width 64px
				display block
				position absolute
				top 0
				left 4px
				right 0
				bottom 0
				margin auto
				animation load-img-animate 2.5s linear infinite
			.text
				color #fff
				font-weight 700
				font-size 14px
				line-height 14px
				width 100%
				height 14px
				display inline-block
				text-align center
				position absolute
				top 170px
				bottom 0
				margin auto
		@keyframes load-background-animate
			0%
				background #fe6a5d
			20%	
				background #ffde54
			40%
				background #6bdb84
			60%
				background #52aeff
			80%
				background #7352b5
			100%
				background #fe6a5d
		@keyframes load-img-animate
			0%
				top 30px
			50%
				top -30px
			100%
				top 30px
</style>
