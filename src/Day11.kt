import java.util.*

private class Monkey(
    val operation: (Long) -> Long,
    val divisor: Long,
    val trueMonkey: Int,
    val falseMonkey: Int,
    vararg elements: Int
) {
    private val items = LinkedList(elements.map { it.toLong() }.toList())
    var inspections = 0L

    fun test(x: Long) = x.mod(divisor) == 0L
    fun hasItems() = items.isNotEmpty()
    fun process(monkeys: List<Monkey>, divide: Boolean, globalDivisor: Long) {
        inspections++
        var e = items.removeFirst() % globalDivisor
        e = operation(e)
        if (divide) {
            e /= 3
        }
        monkeys[if (test(e)) trueMonkey else falseMonkey].items.add(e)
    }
}

private fun List<Monkey>.round(divide: Boolean) {
    val globalDivisor = map { it.divisor }.fold(1L) { x, y -> x * y }
    for (m in this) {
        while (m.hasItems()) {
            m.process(this, divide, globalDivisor)
        }
    }
}


private fun part1(monkeys: List<Monkey>): Long {
    repeat(20) {
        monkeys.round(true)
    }
    val (t1, t2) = monkeys.map { it.inspections }.sortedDescending().take(2)
    return t1 * t2
}

private fun part2(monkeys: List<Monkey>): Long {
    repeat(10000) {
        monkeys.round(false)
    }
    val (t1, t2) = monkeys.map { it.inspections }.sortedDescending().take(2)
    return t1 * t2
}


fun main() {
    check(
        part1(
            listOf(
                Monkey({ it * 19 }, 23, 2, 3, 79, 98),
                Monkey({ it + 6 }, 19, 2, 0, 54, 65, 75, 74),
                Monkey({ it * it }, 13, 1, 3, 79, 60, 97),
                Monkey({ it + 3 }, 17, 0, 1, 74),
            )
        ) == 10605L
    )
    println(
        part1(
            listOf(
                Monkey({ it * 13 }, 19, 6, 2, 91, 66),
                Monkey({ it + 7 }, 5, 0, 3, 78, 97, 59),
                Monkey({ it + 6 }, 11, 5, 7, 57, 59, 97, 84, 72, 83, 56, 76),
                Monkey({ it + 5 }, 17, 6, 0, 81, 78, 70, 58, 84),
                Monkey({ it + 8 }, 7, 1, 3, 60),
                Monkey({ it * 5 }, 13, 7, 4, 57, 69, 63, 75, 62, 77, 72),
                Monkey({ it * it }, 3, 5, 2, 73, 66, 86, 79, 98, 87),
                Monkey({ it + 2 }, 2, 1, 4, 95, 89, 63, 67),
            )
        )
    )

    check(
        part2(
            listOf(
                Monkey({ it * 19 }, 23, 2, 3, 79, 98),
                Monkey({ it + 6 }, 19, 2, 0, 54, 65, 75, 74),
                Monkey({ it * it }, 13, 1, 3, 79, 60, 97),
                Monkey({ it + 3 }, 17, 0, 1, 74),
            )
        ) == 2713310158L
    )
    println(
        part2(
            listOf(
                Monkey({ it * 13 }, 19, 6, 2, 91, 66),
                Monkey({ it + 7 }, 5, 0, 3, 78, 97, 59),
                Monkey({ it + 6 }, 11, 5, 7, 57, 59, 97, 84, 72, 83, 56, 76),
                Monkey({ it + 5 }, 17, 6, 0, 81, 78, 70, 58, 84),
                Monkey({ it + 8 }, 7, 1, 3, 60),
                Monkey({ it * 5 }, 13, 7, 4, 57, 69, 63, 75, 62, 77, 72),
                Monkey({ it * it }, 3, 5, 2, 73, 66, 86, 79, 98, 87),
                Monkey({ it + 2 }, 2, 1, 4, 95, 89, 63, 67),
            )
        )
    )
}
