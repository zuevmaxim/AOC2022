import kotlin.math.max

private fun List<String>.toGrid(): List<IntArray> = this.map { line -> line.map { it.toString().toInt() }.toIntArray() }

private fun List<IntArray>.isVisible(i: Int, j: Int): Boolean {
    val vertical = { x: Int -> this[x][j] < this[i][j] }
    val horizontal = { x: Int -> this[i][x] < this[i][j] }
    return (0 until i).all(vertical) || (i + 1 until size).all(vertical)
        || (0 until j).all(horizontal) || (j + 1 until this[0].size).all(horizontal)
}

private fun part1(input: List<String>): Int {
    val g = input.toGrid()
    val h = g.size
    val w = g[0].size

    val exterior = (h + w) * 2 - 4
    var interior = 0
    for (i in 1 until h - 1) {
        for (j in 1 until w - 1) {
            if (g.isVisible(i, j)) {
                interior++
            }
        }
    }

    return exterior + interior
}

private fun List<IntArray>.score(i: Int, j: Int): Int {
    var up = 0
    for (x in (0 until i).reversed()) {
        up++
        if (this[x][j] >= this[i][j]) break
    }

    var down = 0
    for (x in (i + 1 until size)) {
        down++
        if (this[x][j] >= this[i][j]) break
    }

    var left = 0
    for (x in (0 until j).reversed()) {
        left++
        if (this[i][x] >= this[i][j]) break
    }

    var right = 0
    for (x in (j + 1 until this[0].size)) {
        right++
        if (this[i][x] >= this[i][j]) break
    }


    return up * down * left * right
}

private fun part2(input: List<String>): Int {
    val g = input.toGrid()
    val h = g.size
    val w = g[0].size
    var max = 0
    for (i in 1 until h - 1) {
        for (j in 1 until w - 1) {
            max = max(max, g.score(i, j))
        }
    }
    return max
}

fun main() {
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
