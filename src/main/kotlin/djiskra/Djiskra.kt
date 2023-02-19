package djiskra


fun main() {
    val graph = generateGraph().toMutableList()

    printGraph(evalNodes(graph))
}

fun generateGraph(): List<Path> {
    val nodes = mutableListOf<Node>()
    nodes.add(Node("0", "s", shortestPathLength = 0))

    val lengthOfMap = 5
    for (i in 1..lengthOfMap) {
        nodes.add(Node(i.toString(), "p"))
    }
    val graph = mutableListOf<Path>()
    for (i in 0..nodes.size - 2) {
        graph.add(Path(nodes[i], nodes[(0 until nodes.size - 1).random()], (1 until 10).random()))
    }
    val finish = Node((lengthOfMap + 1).toString(), "f")
    graph.add(Path(graph.last().from, finish, 1))

    return graph
}

fun evalNodes(graph: MutableList<Path>): List<Path> {
    while (!wasEverythingVisited(graph)) {
        for (path in graph) {
            path.from.visitedAsSmallest = true
            val neighbors = findNeighbors(path, graph)
            if (path.from.shortestPathLength != Int.MAX_VALUE) {
                for (neighbor in neighbors) {
                    if (neighbor.length + neighbor.from.shortestPathLength < neighbor.to.shortestPathLength) {
                        neighbor.to.shortestPathLength = neighbor.length + neighbor.from.shortestPathLength
                    }
                }
            }
        }
    }
    return graph
}

fun findNeighbors(searchingForPath: Path, paths: List<Path>): List<Path> {
    val output = mutableListOf<Path>()
    for (i in paths) {
        if (searchingForPath.from == i.from) {
            output.add(i)
        }
    }
    return output
}

fun wasEverythingVisited(graph: List<Path>): Boolean {
    for (i in graph) {
        if (!i.from.visitedAsSmallest) {
            return false
        }
    }
    return true
}

fun printGraph(graph: List<Path>) {
    for (i in graph) {
        println("${i.from.name}(${i.from.shortestPathLength}) type=${i.from.type} -> ${i.to.name}(${i.to.shortestPathLength}) type=${i.to.type} #${i.length}")
    }
}

