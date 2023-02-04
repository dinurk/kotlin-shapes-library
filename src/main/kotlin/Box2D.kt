class Box2D(top: Double, right: Double, bottom: Double, left: Double) {

    init {
        if(top > bottom)
            throw IllegalArgumentException("значение top должно быть меньше значения bottom")
        if(right < left)
            throw IllegalArgumentException("значение right должно быть меньше значения left")
    }

    var top: Double = top
        set(value) {
            if(value < bottom)
                field = value
        }

    var right: Double = right
        set(value) {
            if(value > left )
                field = value
        }

    var bottom: Double = bottom
        set(value) {
            if(value > top)
                field = value
        }

    var left: Double = left
        set(value) {
            if(value < right)
                field = value
        }

    fun clone(): Box2D {
        return Box2D(top, right, bottom, left)
    }
}