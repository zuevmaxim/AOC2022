private enum class Element(val score: Int) {
    Rock(1), Paper(2), Scissors(3);

    fun roundScore(other: Element): Int {
        if (this == other) return 3
        if ((this.ordinal + 1) % 3 == other.ordinal) return 0
        return 6
    }
}

private enum class Result(val shift: Int) {
    Win(1), Draw(0), Lose(-1);

    fun findAnswer(element: Element): Element {
        return Element.values()[(element.ordinal + shift + 3) % 3]
    }
}

private val elementNames = hashMapOf(
    "A" to Element.Rock, "B" to Element.Paper, "C" to Element.Scissors,
    "X" to Element.Rock, "Y" to Element.Paper, "Z" to Element.Scissors,
)

private val resultNames = hashMapOf("X" to Result.Lose, "Y" to Result.Draw, "Z" to Result.Win)

private fun part1(input: List<String>): Int {
    return input.sumOf { round ->
        val (a, b) = round.split(" ").map { elementNames[it]!! }
        b.score + b.roundScore(a)
    }
}

private fun part2(input: List<String>): Int {
    return input.sumOf { round ->
        val (a, b) = round.split(" ")
        val other = elementNames[a]!!
        val result = resultNames[b]!!
        val answer = result.findAnswer(other)
        answer.score + answer.roundScore(other)
    }
}

fun main() {
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
