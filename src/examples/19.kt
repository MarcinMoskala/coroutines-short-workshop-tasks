package examples.n2

import examples.massiveRun
import kotlinx.coroutines.experimental.*

private var counter = 0

fun main(args: Array<String>) = runBlocking {
    val counterContext =
            newSingleThreadContext("CounterContext")

    GlobalScope.massiveRun {
        withContext(counterContext) {
            counter++
        }
    }
    println("Counter = $counter")
}