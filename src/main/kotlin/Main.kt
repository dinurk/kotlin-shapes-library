import shape2d.Circle
import shape2d.Shape2DGroup

fun main() {

    val circle1 = Circle(0.0,0.0, 1.0)
    val circle2 = Circle(2.0,0.0, 1.0)
    val circle3 = Circle(4.0,0.0, 1.0)

    val group = Shape2DGroup(circle1, circle2, circle3)
    println(group)

    group.shift(3.0, 0.0)
    println(group)

    for(i in 1..10) {
        group.shift(-1.0, 0.0)
        println(group)
    }
}