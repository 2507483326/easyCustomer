<template>
	<div class="about_me">
		<div class="trundle_page" ref="pageBox">
			<!-- list -->
			<!-- 封面 -->
			<div class="page page_cover" :class="{active:page == 0}">
				<div class="wrap">
					<h1>RESUME</h1>
					<p>JAVA PROGRAMMER</p>
				</div>
			</div>
			<!-- list -->
			<!-- 关于我 -->
			<div class="page page_me" :class="{active:page == 1}">
				<div class="panel_box">
					<div class="wrap">
						<div class="img_box avatar">
							<img src="./img/avatar.jpg" alt="avatar" title="avatar">
						</div>
						<div class="content">
							<h1>Hello 我是李涛</h1>
							<p>我是一名湖南娄底职业技术学院的毕业生，精通HTML与JAVA开发，曾在大二时在上海乐知网络科技有限公司兼职网站开发，HTML网站编写不在话下，同时对于最新的web框架如React、Vue框架也非常熟悉，此外我还接触过基于java语言的SSM框架后台编写，能熟练使用spring、spring mvc、Mybatis等框架进行网站后台编写。</p>
							<ul>
								<li><img src="./img/qq.png" alt="qq" title="qq">2507483326</li>
								<li><img src="./img/phone.png" alt="phone" title="phone">18390588753</li>
							</ul>
							<div class="link_box">
								<div @click="addFirend" class="contact_me">联系我</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- list -->
			<div class="page capacity" :class="{active:page == 2}">
				<div class="wrap">
					<ul>
						<!-- list -->
						<li>
							<div class="circle">
								<svg class="circle_html" width="200" height="200" xmlns="http://www.w3.org/2000/svg">
									<circle id="backdrop" r="90" cy="100" cx="100" stroke-width="5" stroke="#FCE6A4" fill="none"/>
									<circle id="progress" r="90" cy="100" cx="100" stroke-width="5" stroke="#EFB917" fill="none"/> 
								</svg>
								<div class="text">90%</div>
							</div>
							<h1>HTML</h1>
						</li>
						<!-- list -->
						<li>
							<div class="circle">
								<svg class="circle_js" width="200" height="200" xmlns="http://www.w3.org/2000/svg">
									<circle id="backdrop" r="90" cy="100" cx="100" stroke-width="5" stroke="#BEE3F7" fill="none"/>
									<circle id="progress" r="90" cy="100" cx="100" stroke-width="5" stroke="#45AEEA" fill="none"/> 
								</svg>
								<div class="text">70%</div>
							</div>
							<h1>JAVASCRIPT</h1>
						</li>
						<!-- list -->
						<li>
							<div class="circle">
								<svg class="circle_css" width="200" height="200" xmlns="http://www.w3.org/2000/svg">
									<circle id="backdrop" r="90" cy="100" cx="100" stroke-width="5" stroke="#FCE6A4" fill="none"/>
									<circle id="progress" r="90" cy="100" cx="100" stroke-width="5" stroke="#EFB917" fill="none"/> 
								</svg>
								<div class="text">80%</div>
							</div>
							<h1>CSS</h1>
						</li>
						<!-- list -->
						<li>
							<div class="circle">
								<svg class="circle_java" width="200" height="200" xmlns="http://www.w3.org/2000/svg">
									<circle id="backdrop" r="90" cy="100" cx="100" stroke-width="5" stroke="#F8F9B6" fill="none"/>
									<circle id="progress" r="90" cy="100" cx="100" stroke-width="5" stroke="#D2D558" fill="none"/> 
								</svg>
								<div class="text">80%</div>
							</div>
							<h1>JAVA</h1>
						</li>
					</ul>
				</div>
			</div>
			<!-- list -->
			<div class="page page_works">
				<div class="wrap">
					<div class="list_box">
						<!-- list -->
						<a href="http://www.worlduc.com/SpaceShow/Index.aspx?uid=2522120" class="list">
							<div class="img_box">
								<img src="./img/list_01.png" alt="list" title="list">
							</div>
							<div class="content">
								<h1>新闻网站</h1>
								<div class="solid"></div>
								<p>这个网站是我在学校做的一个毕业设计，所有的文档，与截图都在这个展示空间中可以看到，前端采用的bootStrap框架，与react相结合，后端采用SSM框架搭建而成</p>
							</div>
						</a>
						<!-- list -->
						<a href="http://vip.hiknow.com/" class="list">
							<div class="img_box">
								<img src="./img/list_02.png" alt="list" title="list">
							</div>
							<div class="content">
								<h1>乐知vip页面</h1>
								<div class="solid"></div>
								<p>这个页面是我在乐知做兼职时做的一个vip单页</p>
							</div>
						</a>
						<!-- list -->
						<a href="http://www.hiknow.com/wap/sessionhall.do" class="list">
							<div class="img_box">
								<img src="./img/list_03.png" alt="list" title="list">
							</div>
							<div class="content">
								<h1>乐知手机端页面</h1>
								<div class="solid"></div>
								<p>这个页面是我在乐知兼职时做的手机端页面</p>
							</div>
						</a>
						<!-- list -->
						<a href="http://www.hiknow.com/market/2016/USandUK/index.shtml" class="list">
							<div class="img_box">
								<img src="./img/list_04.png" alt="list" title="list">
							</div>
							<div class="content">
								<h1>乐知活动页面</h1>
								<div class="solid"></div>
								<p>这个页面是我在乐知兼职时做的活动页面</p>
							</div>
						</a>
						<!-- list -->
						<a href="http://www.hiknow.com/2014/us/plan.html" class="list">
							<div class="img_box">
								<img src="./img/list_05.png" alt="list" title="list">
							</div>
							<div class="content">
								<h1>乐知计划页面</h1>
								<div class="solid"></div>
								<p>这个页面是我在乐知兼职时做的计划页面</p>
							</div>
						</a>
						<!-- list -->
						<a href="http://www.hiknow.com/2014/app/index.html" class="list">
							<div class="img_box">
								<img src="./img/list_06.png" alt="list" title="list">
							</div>
							<div class="content">
								<h1>乐知App页面</h1>
								<div class="solid"></div>
								<p>这个页面是我在乐知兼职时做的App页面</p>
							</div>
						</a>
					</div>
				</div>
			</div>
		</div>
		<ul class="trundle_controll">
			<li :class="{'active':page===key}" v-for="(item, key) in controlls" v-bind:key="key" v-bind:data-index="key" @click="select(key)">
				<div class="dot"></div>
				<div class="text">{{item.text}}</div>
			</li>
		</ul>
	</div>
</template>

<script>
	import velocity from 'velocity-animate'

	const ANIMATE_START = true
	const ANIMATE_FINISH = false
	
	export default {
		data () {
			return {
				page:0,
				maxPage:{
					type:Number
				},
				flag:ANIMATE_FINISH,
				controlls:[
					{
						num:0,
						text:'封面'
					},
					{
						num:1,
						text:'关于我'
					},
					{
						num:2,
						text:'技能展示'
					},
					{
						num:3,
						text:'作品展示'
					}
				]
			}
		},
		watch:{
			page () {
				console.log(this.page)
				velocity(this.$refs.pageBox, {
					translateY: -(100 * this.page) + '%'
				}, {
					duration: 1000,
					easing: [0.86, 0, 0.07, 1],
					complete: () => {
						console.log('done')
						this.flag = ANIMATE_FINISH
					}
				})
			}
		},
		mounted () {
			this.initPageSize()
			this.$nextTick(() => {
				let scrollFunc = (e) => {
					e = e || window.event
					let detail
					if (e.wheelDelta) {
					    detail = e.wheelDelta
					} else if (e.detail) {
					    detail = e.detail
					}
					if (detail > 0) {
						this.pageReduce()
					} else {
						this.pageAdd()
					}
				}
				if (document.addEventListener) {
				    document.addEventListener('DOMMouseScroll', scrollFunc, false)
				}
				window.onmousewheel = document.onmousewheel = scrollFunc
			})
		},
		methods:{
			pageAdd () {
				// 判断动画是否开始
				if (this.flag) {
					return
				}
				if (this.page < this.maxPage) {
					this.page = this.page + 1
					this.flag = ANIMATE_START
				}
			},
			pageReduce () {
				// 判断动画是否开始
				if (this.flag) {
					return
				}
				if (this.page > 0) {
					this.page = this.page - 1
					this.flag = ANIMATE_START
				}
			},
			initPageSize () {
				let maxPage = this.$refs.pageBox.getElementsByClassName('page').length
				this.maxPage = maxPage - 1
			},
			select (key) {
				// 判断动画是否开始
				if (this.flag) {
					return
				}
				this.page = key
				this.flag = ANIMATE_START
			},
			addFirend () {
				window.open('tencent://AddContact/?fromId=50&fromSubId=1&subcmd=all&uin=2507483326')
			}
		}
	}
</script>

<style  lang="stylus" scoped>
	@import '../../common/stylus/mixin.styl'
	.about_me
		position absolute
		top 0
		left 0
		bottom 50px
		width 100%
		overflow hidden
		.trundle_page
			position relative
			height 100%
			width 100%
			.page
				height 100%
				width 100%
				&.page_cover
					background #1e9fb4
					.wrap
						text-align center
						padding-top 300px
						h1
							font-size 50px
							color #fff
							padding 20px
							position relative
						p
							font-size 25px
							position relative
							color #fff
						@keyframes page_cover_h1
							0%
								top -60px
							30%
								top 0
							55%
								top -30px
							75%
								top 0
							85%
								top
									-10px
							100%
								top 0
						@keyframes page_cover_p
							0%
								top 30px
								opacity 0
							100%
								top 0
								opacity 1
					&.active
						h1
							animation page_cover_h1 1000ms cubic-bezier(0,0,.58,1) 500ms both
						p
							animation page_cover_p 500ms cubic-bezier(0,0,.58,1) 500ms both
				&.page_me
					background-image url('./img/counter-bg.jpg')
					background-repeat no-repeat
					background-position center center
					position relative
					.panel_box
						width 100%
						position absolute
						top 0
						bottom 0
						margin auto
						height 250px
						padding 20px
						background rgba(0,0,0,.7)
						.avatar
							width 350px
							height 250px
							position relative
							float left
							margin-right 40px
							img
								width 320px
								height 320px
								position absolute
								border-radius 50%
								top 50%
								left 50%
								transform translate3d(-50%,-50%,0)
						.content
							float left
							width 600px
							h1
								font-size 35px
								line-height 1em
								margin 20px 0 20px
								color #fff
							p
								margin-bottom 10px
								font-size 14px
								color #fff
							ul
								overflow hidden
								margin-bottom 15px
								li
									font-size 16px
									height 16px
									float left
									color #fff
									margin-right 30px
									line-height 1em
									img
										vertical-align top
										height 16px
										margin 1px 5px 0 0
							.link_box
								.contact_me
									display block
									border 1px solid #449d44
									color #fff
									font-size 16px
									width 150px
									height 40px
									line-height 40px
									text-align center
									border-radius 3px
									cursor pointer
									transition all .3s 
									&:hover
										background #449d44
				&.page_works
					background #ffdaa3
					.wrap
						position relative
						height 100%
					.list_box
						height 400px
						position absolute
						top 0
						bottom 0
						left 0
						right 0
						margin auto 
						overflow hidden
						.list
							height 200px
							float left
							width 33.33%
							position relative
							overflow hidden
							.img_box
								height 100%
								width 100%
								img
									display block
									height 100%
									width 100%
							&:hover
								.content
									bottom 0
							.content
								position absolute
								left 0
								bottom -100%
								width 100%
								height 100%
								background #2c3f52
								opacity .8
								transition all .5s ease-out
								h1
									text-align center
									font-size 25px
									line-height 1em
									color #f8f8f8
									padding 20px 0 10px
								.solid
									width 100px
									height 3px
									background rgb(0,128,128)
									margin 0 auto
								p
									padding 20px 0
									width 80%
									margin 0 auto
									color #fff
									line-height 1.5em
									font-size 14px
				&.capacity
					text-align center
					position relative
					background #f98884
					ul
						position absolute
						height 264px
						top 0
						bottom 0
						left 0
						right 0
						margin auto
						li
							display inline-block
							padding 0 20px
							.circle
								height 200px
								width 200px
								position relative
								margin-bottom 20px
								.text
									position absolute
									top 0
									bottom 0
									left 0
									right 0
									font-size 40px
									text-align center
									height 40px
									line-height 1em
									margin auto
									color #fff
								svg
									width 200px
									height 200px
									transform rotate(-90deg)
									&.circle_html
										#progress
											svg-circle(90,90,'progress01')
									&.circle_js
										#progress
											svg-circle(90,70,'progress02')
									&.circle_css
										#progress
											svg-circle(90,80,'progress03')
									&.circle_java
										#progress
											svg-circle(90,80,'progress04')
							h1
								font-size 20px
								width 198px
								height 40px
								line-height 40px
								text-align center
								border 2px solid #fff
								position relative
								color #fff
								top 30px
								opacity 0
								transition all 1s
								&:before
									content ''
									width 15px
									height 15px
									border 2px solid #fff
									border-bottom-color transparent
									border-left-color transparent
									position absolute
									left 50%
									top -17px
									background #f98884
									transform rotate(-45deg) translate3d(-50%,0,0)
								@keyframes h1_fade_top
									100%
										opacity 1
										top 0
					&.active
						h1
							animation h1_fade_top 500ms ease-out 500ms both
						.circle
							.circle_html
								#progress
									animation progress01 500ms ease-out 500ms both
							.circle_js
								#progress
									animation progress02 500ms ease-out 500ms both
							.circle_css
								#progress
									animation progress03 500ms ease-out 500ms both
							.circle_java
								#progress
									animation progress04 500ms ease-out 500ms both
		.trundle_controll
			width 20px
			height 100%
			position fixed
			top 0
			right 40px
			display block
			writing-mode: tb-rl
			text-align center
			li
				height 12px
				width 12px
				display inline-block
				margin 4px auto 
				position relative
				cursor pointer
				.dot
					position absolute
					top 0
					bottom 0
					left 0
					right 0
					width 6px
					height 6px
					border-radius 50%
					border 1px solid #6c6c6c
					transition all .3s ease-out
					background #6c6c6c
					display block
					margin  auto
				.text
					font-size 10px
					height 12px
					line-height 12px
					color #fff
					position absolute
					width 0
					left -90px
					top 0
					text-align right
					writing-mode horizontal-tb
					transition all .4s ease-out
					opacity 0
				&.active
					.dot
						background #fff
						width 10px
						height 10px
				&:hover
					.text
						opacity 1
						width 80px
</style>
