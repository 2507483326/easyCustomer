<template>
	<div id="circleBg_box" @mousemove.stop.prevent="mousemove($event)">
		<canvas id="circleBg_canvas" ref="circleBg_canvas"></canvas>
	</div>
</template>

<script>
	import {Circle, CurrentCircle} from 'common/js/circleBgClass'
	export default {
		data () {
			return {
				currentCircle: null
			}
		},
		mounted () {
			// 初始化变量
			let canvas = document.getElementById('circleBg_canvas')
			let ctx = canvas.getContext('2d')
			let w = canvas.width = canvas.offsetWidth
			let h = canvas.height = canvas.offsetHeight
			let circles = []
			this.currentCircle = new CurrentCircle(0, 0)
			let requestAnimationFrame = this.getAnimationFrame()
			let _this = this
			let draw = function () {
				ctx.clearRect(0, 0, w, h)
				for (let i = 0; i < circles.length; i++) {
					circles[i].move(w, h)
					circles[i].drawCircle(ctx)
					for (let j = i + 1; j < circles.length; j++) {
						circles[i].drawLine(ctx, circles[j])
					}
				}
				console.log(_this.currentCircle.x)
				if (_this.currentCircle.x) {
					_this.currentCircle.drawCircle(ctx)
					for (let k = 1; k < circles.length; k++) {
						_this.currentCircle.drawLine(ctx, circles[k])
					}
				}
				requestAnimationFrame(draw)
			}

			let init = function (num) {
				for (let i = 0; i < num; i++) {
					circles.push(new Circle(Math.random() * w, Math.random() * h))
				}
				draw()
			}
			init(80)
		},
		methods:{
			getAnimationFrame () {
				return window.requestAnimationFrame || window.mozRequestAnimationFrame || window.webkitRequestAnimationFrame || window.msRequestAnimationFrame
			},
			mousemove (event) {
				event = event || window.event
			    this.currentCircle.x = event.clientX
			    this.currentCircle.y = event.clientY
			}
		}
	}
</script>

<style  lang="stylus" scoped>
	#circleBg_box
		width 100%
		height 100%
		position fixed
		top 0
		left 0
		z-index -999999
	#circleBg_canvas
		width 100%
		height 100%
</style>
