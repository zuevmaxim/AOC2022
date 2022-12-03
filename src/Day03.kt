private fun priority(c: Char): Int {
    if (c in 'a'..'z') return c - 'a' + 1
    check(c in 'A'..'Z')
    return c - 'A' + 27
}

private fun part1(input: List<String>) = input.sumOf { line ->
    val m = line.length / 2
    val (left, right) = line.take(m).toSet() to line.drop(m).toSet()
    val intersect = left.intersect(right)
    intersect.sumOf { priority(it) }
}

private fun part2(input: List<String>): Int = input.withIndex().groupBy { it.index / 3 }.map { g -> g.value.map { it.value } }.sumOf { group ->
    group[0].toSet().intersect(group[1].toSet()).intersect(group[2].toSet()).sumOf { priority(it) }
}

fun main() {
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
