package shape2d

import Box2D
import Scene

class Point2D(x: Double, y: Double) : Shape2D(x, y) {

    init {
        this.x = if(isValidX(x)) x else {
            println("Point2D: invalid x value was passed to class constructor: $x, value will be replaced by default value: 0.0");
            0.0
        }
        this.y = if(isValidY(y)) y else {
            println("Point2D: invalid y value was passed to class constructor: $y, value will be replaced by default value: 0.0");
            0.0
        }
    }

    override fun isValidX(value: Double): Boolean {
        return value >= 0 && value <= Scene.WIDTH
    }

    override fun isValidY(value: Double): Boolean {
        return value >= 0 && value <= Scene.HEIGHT
    }

    override fun draw() {
        print("point drawing at { x: $x, y: $y }")
    }

    override fun bounds(): Box2D {
        return Box2D(y, x, y, x)
    }

    override fun toString(): String {
        return "Point: { " +
                "x: $x, " +
                "y: $y " +
                "}"
    }
}