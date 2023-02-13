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
        graph.add(Path(nodes[i], nodes[i + 1], 1))
    }


}

fun printGraph(graph: List<Path>) {
    for (i in graph) {
        println("${i.from.name} type=${i.from.type} -> ${i.to.name} type=${i.to.type} #${i.length}")
    }
}


fun findShortestPath(graph: List<Path>) {
    val neighbors = findNeighbors(graph[0].from, graph)
    val dolgit = editValueOfPathToNeighbor(graph[0].from, neighbors)
    printGraph(dolgit)
}

fun findNeighbors(node: Node, graph: List<Path>): List<Path>{
    val output = mutableListOf<Path>()
    for (i in graph){
        if (i.from.name == node.name) output.add(i)
    }
    return output
}

fun editValueOfPathToNeighbor(node: Node, paths: List<Path>): List<Path> {
    val output = mutableListOf<Path>()
    for (path in paths) {
        if (node.shortestPathLength + path.length < path.to.shortestPathLength) {
            path.to.shortestPathLength = node.shortestPathLength + path.length

        }
    }

    return output
}





















