package djiskra


fun main() {
    val graph = evalNodes(generateGraph().toMutableList())

    println("Graf")
    printGraph(graph)
    println("--------------------")
    println("nejkratší cesta")
    printGraph(findShortestPath(graph, graph[0].from, graph.last().to))
}

fun findShortestPath(
    graph: List<Path>,
    from: Node,
    to: Node,
    pathToEnd: MutableSet<Path> = mutableSetOf(),
): List<Path> {
    val neighbors = findNeighbors(from, graph)
    var smallestNeighbor = neighbors[0]

    for (neighbor in neighbors) {
        if (neighbor.to.type == to.type) {
            pathToEnd.add(neighbor)
            return pathToEnd.toList()
        }
        if (neighbor.to.shortestPathLength < smallestNeighbor.to.shortestPathLength) {
            smallestNeighbor = neighbor
        }
    }
    pathToEnd.add(smallestNeighbor)
    return findShortestPath(graph,smallestNeighbor.to,to, pathToEnd)

}


fun generateGraph(): List<Path> {
    val nodes = mutableListOf<Node>()
    nodes.add(Node("0", "s", shortestPathLength = 0))

    val lengthOfMap = 5
    for (i in 1..lengthOfMap) {
        nodes.add(Node(i.toString(), "p"))
    }
    val graph = mutableListOf<Path>()
    for (i in 0 until nodes.size - 2) {
        graph.add(Path(nodes[i], nodes[i + 1], (1 until 10).random()))
    }
    val finish = Node((lengthOfMap + 1).toString(), "f")
    graph.add(Path(graph[(0 until graph.size).random()].from, finish, 1))

    return graph
}

fun evalNodes(graph: MutableList<Path>): List<Path> {
    while (!wasEverythingVisited(graph)) {
        for (path in graph) {
            path.from.visitedAsSmallest = true
            val neighbors = findNeighbors(path.from, graph)
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

fun findNeighbors(searchingForNode: Node, paths: List<Path>): List<Path> {
    val output = mutableListOf<Path>()
    for (i in paths) {
        if (searchingForNode == i.from) {
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

