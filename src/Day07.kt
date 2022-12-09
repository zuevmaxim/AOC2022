private fun part1(input: List<String>): Int {
    val root = parse(input)
    val dirs = root.toDirList()
    return dirs.filter { it.size <= 100000 }.sumOf { it.size }
}

private fun part2(input: List<String>): Int {
    val root = parse(input)
    val dirs = root.toDirList()
    val currentData = root.size
    val currentSpace = 70000000 - currentData
    val requiredSpace = 30000000
    val toDelete = requiredSpace - currentSpace
    return dirs.filter { it.size > toDelete }.minOf { it.size }
}


private interface MockElement {
    val size: Int
    val name: String
    val children: List<MockElement>
}

private data class MockFile(override val name: String, override val size: Int) : MockElement {
    override val children get() = emptyList<MockElement>()
}

private data class MockDir(override val name: String) : MockElement {
    override val children = mutableListOf<MockElement>()
    override val size: Int get() = children.sumOf { it.size }

    fun toDirList(): List<MockDir> = mutableListOf<MockDir>().also { this.addToList(it) }

    private fun addToList(list: MutableList<MockDir>) {
        list.add(this)
        for (dir in children) {
            if (dir is MockDir) dir.addToList(list)
        }
    }
}


private fun parse(input: List<String>): MockDir {
    val root = MockDir("/")
    check(input[0] == "$ cd /")
    var i = 1
    val stack = mutableListOf(root)
    while (i < input.size) {
        if (input[i].startsWith("$ cd ..")) {
            stack.removeLast()
        } else if (input[i].startsWith("$ cd ")) {
            val dir = MockDir(input[i].substring("$ cd ".length))
            stack.last().children.add(dir)
            stack.add(dir)
        } else if (input[i] == "$ ls") {

        } else if (input[i].startsWith("dir ")) {

        } else {
            val (size, name) = input[i].split(" ")
            stack.last().children.add(MockFile(name, size.toInt()))
        }
        i++
    }

    return root
}


fun main() {
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
