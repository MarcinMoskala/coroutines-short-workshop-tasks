package examples.new2

import examples.massiveRun
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withContext

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