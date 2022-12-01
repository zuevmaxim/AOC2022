fun main() {
    fun part1(input: List<String>): Int {
        var current = 0
        var result = 0
        fun updateMax() {
            if (result < current) result = current
            current = 0
        }
        for (s in input) {
            if (s.isEmpty()) {
                updateMax()
            } else {
                current += s.toInt()
            }
        }
        updateMax()
        return result
    }

    fun part2(input: List<String>): Int {
        var current = 0
        val result = mutableListOf<Int>()
        for (s in input) {
            if (s.isEmpty()) {
                result.add(current)
                current = 0
            } else {
                current += s.toInt()
            }
        }
        result.add(current)
        result.sortDescending()
        return result.take(3).sum()
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
