package djiskra

class Node(val name: String, val type: String, visitedAsSmallest: Boolean = false, var shortestPathLength: Int = Int.MAX_VALUE)
