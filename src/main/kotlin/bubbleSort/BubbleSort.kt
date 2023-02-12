package bubbleSort


fun main(){
    val input = (1..10).toList().shuffled()
    val algo = BubbleSort()
    println(algo.bubbleSort(input))

}



class BubbleSort {
    fun bubbleSort(input: List<Int>): List<Int> {
        val output = input.toMutableList()
        var end = false

        while (!end) {
            end = true
            for (i in output.indices) {
                if (i + 1 < output.size) {
                    if (output[i] > output[i + 1]) {
                        val holder = output[i]
                        output[i] = output[i + 1]
                        output[i + 1] = holder
                        end = false
                    }
                }
            }
        }
        return output
    }
}

