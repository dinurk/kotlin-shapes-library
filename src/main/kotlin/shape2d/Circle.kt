package shape2d

import Box2D
import Scene

class Circle(x: Double, y: Double, radius: Double) : Shape2D(x, y) {

    var radius: Double = radius
        set(value) {
            if(!isValidRadius(value)) {
                println("Circle.setRadius(value): invalid radius value: $value, value will be ignored")
                return
            }
            field = value
            x = if(isValidX(x)) x else radius
            y = if(isValidY(y)) y else radius
        }

    init {
        this.radius = if (isValidRadius(radius)) {
            radius
        } else {
            println("Circle: invalid radius value was passed to class constructor: $radius, value will be replaced by default value: ${defaultRadius()}")
            defaultRadius()
        }
        this.x = if(isValidX(x)) x else radius
        this.y = if(isValidY(y)) y else radius
    }

    protected fun defaultRadius(): Double {
        return 1.0
    }

    fun isValidRadius(value: Double): Boolean {
        return value > 0 &&
                value < Scene.WIDTH / 2 &&
                value < Scene.HEIGHT / 2
    }

    override fun isValidX(value: Double): Boolean {
        return value >= 0 + radius && value <= Scene.WIDTH - radius
    }

    override fun isValidY(value: Double): Boolean {
        return value >= 0 + radius && value <= Scene.HEIGHT - radius
    }

    override fun toString(): String {
        return "shape2d.Circle: { " +
                "x: $x, " +
                "y: $y, " +
                "radius: $radius " +
                "}"
    }

    override fun draw() {
        print("circle drawing at { x: $x, y: $y }")
    }

    override fun bounds(): Box2D {
        return Box2D(y - radius, x + radius, y + radius, x - radius)
    }
}