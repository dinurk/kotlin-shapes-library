package shape2d

import Box2D

abstract class Shape2D(x: Double, y: Double) {

    protected open var x: Double = x
        set(value) {
            if(!isValidX(value)) {
                println("Shape2D.setX(value): invalid x value: $value, value will be ignored")
                return
            }
            field = value
        }

    protected open var y: Double = y
        set(value) {
            if(!isValidY(value)) {
                println("Shape2D.setY(value): invalid y value: $value, value will be ignored")
                return
            }
            field = value
        }

    open fun shift(dx: Double, dy: Double) {
        x += dx
        y += dy
    }

    abstract fun isValidX(value: Double): Boolean
    abstract fun isValidY(value: Double): Boolean
    abstract fun draw()
    abstract fun bounds(): Box2D
}