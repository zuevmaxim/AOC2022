private fun solve(input: List<String>, k: Int): Int {
    val s = input[0]
    for (i in 0 until s.length - k) {
        if (s.substring(i, i + k).toSet().size == k) {
            return i + k
        }
    }
    return -1
}

private fun part1(input: List<String>) = solve(input, 4)

private fun part2(input: List<String>) = solve(input, 14)

fun main() {
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
