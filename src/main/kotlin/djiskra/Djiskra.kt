package djiskra


fun main() {
    val nodes = mutableListOf<Node>()
    nodes.add(Node("0", "s", shortestPathLength = 0))

    val lengthOfMap = 5
    for (i in 1..lengthOfMap) {
        nodes.add(Node(i.toString(), "p"))
    }
    nodes.add(Node((lengthOfMap + 1).toString(), "f"))
    val graph = mutableListOf<Path>()
    for (i in 0..nodes.size - 2) {
        graph.add(Path(nodes[i], nodes[i + 1], i + 1))
    }




    findShortestPath(graph)
}


fun printGraph(graph: List<Path>) {
    for (i in graph) {
        println("${i.from.name}(${i.from.shortestPathLength}) type=${i.from.type} -> ${i.to.name}(${i.to.shortestPathLength}) type=${i.to.type} #${i.length}")
    }
}


fun findShortestPath(graph: List<Path>) {
    val output = editValueOfPathToNeighbor(graph[0].from, graph)
    printGraph(output)
}

fun findNeighbors(node: Node, graph: List<Path>): List<String> {
    val output = mutableListOf<String>()
    for (i in graph) {
        if (i.from.name == node.name) output.add(i.to.name)
    }
    return output
}

fun editValueOfPathToNeighbor(node: Node, graph: List<Path>): List<Path> {
    val neighborsName = findNeighbors(node, graph)
    val output = mutableListOf<Path>()
    for (path in graph) {
        if (path.from.name == node.name && path.to.name in neighborsName) {
            if (node.shortestPathLength + path.length < path.to.shortestPathLength) {
                val editedPath = Path(
                    path.from,
                    Node(path.to.name, path.to.type, false, (node.shortestPathLength + path.length)),
                    path.length
                )
                output.add(editedPath)
            }
        } else output.add(path)
    }
    return output
}
