package djiskra


fun main() {
    val nodes = mutableListOf<Node>()
    nodes.add(Node("0", "s"))
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

class Djiskra {

}
