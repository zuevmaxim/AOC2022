import java.awt.Point
import kotlin.math.abs


private val move = hashMapOf("U" to (0 to 1), "D" to (0 to -1), "L" to (-1 to 0), "R" to (1 to 0))

private fun List<String>.toSteps() = sequence {
    val p = Point(0, 0)
    for (line in this@toSteps) {
        val (step, count) = line.split(" ")
        val (dx, dy) = move[step]!!
        repeat(count.toInt()) {
            p.translate(dx, dy)
            yield(p.x to p.y)
        }
    }
}

private fun Sequence<Pair<Int, Int>>.nextElement(): Sequence<Pair<Int, Int>> {
    val point = Point(0, 0)
    return this.map { (headX, headY) ->
        val dx = headX - point.x
        val dy = headY - point.y
        if (abs(dx) > 1 || abs(dy) > 1) {
            val dxx = if (dx == 0) 0 else dx / abs(dx)
            val dyy = if (dy == 0) 0 else dy / abs(dy)
            point.translate(dxx, dyy)
        }
        point.x to point.y
    }
}

private fun part1(input: List<String>) = input.toSteps().nextElement().toHashSet().size

private fun part2(input: List<String>): Int {
    var seq = input.toSteps()
    repeat(9) {
        seq = seq.nextElement()
    }
    return seq.toHashSet().size
}

fun main() {
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 1)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
