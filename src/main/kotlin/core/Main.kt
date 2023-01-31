package core

import core.alga.bubbleSort.BubbleSort

fun main(){
    val input = (1..10).toList().shuffled()
    val algo = BubbleSort()
    println(algo.bubbleSort(input))

}
