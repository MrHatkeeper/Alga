package djiskra

class Node(val name: String, val type: String,var visitedAsSmallest: Boolean = false, var shortestPathLength: Int = Int.MAX_VALUE)
