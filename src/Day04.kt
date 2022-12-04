private fun part1(input: List<String>) = input.count { line ->
    val (a, b) = line.split(",").map { r -> r.split("-").map(String::toInt).toPair() }
    a.first <= b.first && b.second <= a.second ||
        b.first <= a.first && a.second <= b.second
}


private fun part2(input: List<String>) = input.count { line ->
    val (a, b) = line.split(",").map { r -> r.split("-").map(String::toInt).toPair() }
    a.first <= b.first && b.first <= a.second ||
        a.first <= b.second && b.second <= a.second ||
        b.first <= a.first && a.first <= b.second ||
        b.first <= a.second && a.second <= b.second
}

fun main() {
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
