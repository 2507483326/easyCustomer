// 定义圆类
export class Circle {

	// 构造方法 x y r 坐标 半径随机为 1 - 14
	constructor (x, y) {
		this.x = x
		this.y = y
		this.r = Math.random() * 14 + 1
		this._mx = Math.random() * 2 - 1
		this._my = Math.random() * 2 - 1
	}

	// 画圆方法
	drawCircle (ctx) {
		ctx.beginPath()
		ctx.arc(this.x, this.y, this.r, 0, 360)
		ctx.closePath()
		ctx.fillStyle = 'rgba(204, 204, 204, 0.2)'
		ctx.fill()
	}

	// 画线方法
	drawLine (ctx, _circle) {
		let dx = this.x - _circle.x
		let dy = this.y - _circle.y
		let d = Math.sqrt(dx * dx + dy * dy)
		if (d < 150) {
			ctx.beginPath()
			ctx.moveTo(this.x, this.y)
			// 起始点
			ctx.lineTo(_circle.x, _circle.y)
			// 终点
			ctx.closePath()
			ctx.strokeStyle = 'rgba(204, 204, 204, 0.1)'
			ctx.stroke()
		}
	}

	// 移动方法
	move (w, h) {
		this._mx = (this.x < w && this.x > 0) ? this._mx : (-this._mx)
		this._my = (this.y < h && this.y > 0) ? this._my : (-this._my)
		this.x += this._mx / 2
		this.y += this._my / 2
	}
}

// 定义自己的圆
export class CurrentCircle extends Circle {

    // 重写画圆方法
    drawCircle (ctx) {
        ctx.beginPath()
        this.r = (this.r < 14 && this.r > 1) ? this.r + (Math.random() * 2 - 1) : 2
		ctx.arc(this.x, this.y, this.r, 0, 360)
		ctx.closePath()
		ctx.fillStyle = 'rgba(0, 0, 0, 0)'
		ctx.fill()
    }
}
