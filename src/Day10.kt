private fun List<String>.toSignals(): Sequence<Int> {
    var value = 1
    return sequence {
        for (line in this@toSignals) {
            yield(value)
            if (line != "noop") {
                yield(value)
                value += line.substring(5).toInt()
            }
        }
    }
}

private fun part1(input: List<String>): Int {
    val signals = input.toSignals().toList()
    val target = listOf(20, 60, 100, 140, 180, 220)
    return target.sumOf { it * signals[it - 1] }
}

private fun part2(input: List<String>): String {
    val height = 6
    val width = 40
    val result = List(height) { MutableList(width) { '.' } }
    var i = 0
    input.toSignals().forEach { n ->
        if (i % width in n - 1..n + 1) {
            result[i / width][i % width] = '#'
        }
        i++
    }
    return result.joinToString("\n") { it.joinToString("") }
}

fun main() {
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    check(
        part2(testInput) == """
        ##..##..##..##..##..##..##..##..##..##..
        ###...###...###...###...###...###...###.
        ####....####....####....####....####....
        #####.....#####.....#####.....#####.....
        ######......######......######......####
        #######.......#######.......#######.....""".trimIndent()
    )

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
