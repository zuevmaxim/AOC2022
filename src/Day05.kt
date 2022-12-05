private data class State(val stacks: List<MutableList<String>>)
private data class Command(val count: Int, val from: Int, val to: Int)

private fun parseInput(input: List<String>, commands: MutableList<Command>): State {
    val stacksCount = (input[0].length + 1) / 4
    val stacks = List(stacksCount) { mutableListOf<String>() }
    var i = 0
    while (i < input.size) {
        if (!input[i].contains("[")) break
        var lastIndex = 0
        while (true) {
            val index = input[i].indexOf("[", lastIndex)
            if (index == -1) break
            val group = index / 4
            val value = input[i][index + 1].toString()
            stacks[group].add(value)
            lastIndex = index + 1
        }
        i++
    }
    for (stack in stacks) {
        stack.reverse()
    }

    while (i < input.size) {
        if (input[i].startsWith("move ")) {
            val fromIndex = input[i].indexOf(" from ")
            val toIndex = input[i].indexOf(" to ")
            val count = input[i].substring("move ".length, fromIndex).toInt()
            val from = input[i].substring(fromIndex + " from ".length, toIndex).toInt() - 1
            val to = input[i].substring(toIndex + " to ".length).toInt() - 1
            commands.add(Command(count, from, to))
        }
        i++
    }
    return State(stacks)
}


private fun part1(input: List<String>): String {
    val commands = mutableListOf<Command>()
    val state = parseInput(input, commands)
    for (command in commands) {
        repeat(command.count) {
            val last = state.stacks[command.from].removeLast()
            state.stacks[command.to].add(last)
        }
    }
    return state.stacks.joinToString("") { it.last() }
}


private fun part2(input: List<String>): String {
    val commands = mutableListOf<Command>()
    val state = parseInput(input, commands)
    for (command in commands) {
        val top = MutableList(command.count) { state.stacks[command.from].removeLast() }
        state.stacks[command.to].addAll(top.reversed())
    }
    return state.stacks.joinToString("") { it.last() }
}

fun main() {
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
