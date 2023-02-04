package shape2d

import Box2D
import Scene

class Shape2DGroup(): Shape2D(0.0, 0.0) {
    private val shapes: MutableList<Shape2D> = mutableListOf()
    private var bounds: Box2D = Box2D(
        Scene.HEIGHT / 2.0,
        Scene.WIDTH / 2.0,
        Scene.HEIGHT / 2.0,
        Scene.WIDTH / 2.0
    )

    override var x: Double
        get() = super.x
        set(value) {
            val dx = value - super.x
            super.x = value
            if(isValidX(value)) {
                shapes.forEach {
                    it.shift(dx, 0.0)
                }
            }
            countBounds()
        }

    override var y: Double
        get() = super.y
        set(value) {
            val dy = value - super.y
            super.y = value
            if(isValidY(value)) {
                shapes.forEach {
                    it.shift(0.0, dy)
                }
                countBounds()
            }
        }

    constructor(vararg shapes: Shape2D) :this() {
        shapes.forEach {
            add(it)
        }
    }

    fun add(element: Shape2D) {
        shapes.add(element)
        if(shapes.count() == 1) {
            bounds = element.bounds().clone()
            return
        }
        val elementBounds = element.bounds()
        if(elementBounds.left < bounds.left) {
            bounds.left = elementBounds.left
        }
        if(elementBounds.right > bounds.right) {
            bounds.right = elementBounds.right
        }
        if(elementBounds.top < bounds.top) {
            bounds.top = elementBounds.top
        }
        if(elementBounds.bottom > bounds.bottom) {
            bounds.bottom = elementBounds.bottom
        }
    }

    fun remove(element: Shape2D): Boolean {
        if(!shapes.remove(element)) {
            return false
        }

        countBounds()
        return true
    }

    override fun isValidX(value: Double): Boolean {
        val dx = value - x
        return bounds.left + dx >= 0 && bounds.right + dx <= Scene.WIDTH
    }

    override fun isValidY(value: Double): Boolean {
        val dy = value - y
        return bounds.top + dy >= 0 && bounds.bottom + dy <= Scene.HEIGHT
    }

    override fun draw() {
        println("group drawing at { $x, $y } :")
        shapes.forEach {
            print(" ")
            it.draw()
        }
    }

    override fun bounds(): Box2D {
        return bounds.clone()
    }

    private fun countBounds() {

        if(shapes.isEmpty()) {
            bounds = Box2D(0.0, 0.0, 0.0, 0.0)
            return
        }

        bounds = shapes[0].bounds().clone()

        if(shapes.count() == 1) {
            return
        }

        for(i in 1 until shapes.count()) {
            val elementBounds = shapes[i].bounds()
            if(elementBounds.left < bounds.left) {
                bounds.left = elementBounds.left
            }
            if(elementBounds.right > bounds.right) {
                bounds.right = elementBounds.right
            }
            if(elementBounds.top < bounds.top) {
                bounds.top = elementBounds.top
            }
            if(elementBounds.bottom > bounds.bottom) {
                bounds.bottom = elementBounds.bottom
            }
        }
    }

    override fun toString(): String {
        val stringBuffer = StringBuffer("shapesGroup: {\n")
        shapes.forEach {
            stringBuffer.append(it.toString())
            stringBuffer.append(",\n")
        }
        stringBuffer.append("}")
        return stringBuffer.toString()
    }
}