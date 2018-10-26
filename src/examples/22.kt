package examples

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

var counter = 0

fun main(args: Array<String>) = runBlocking {
    GlobalScope.massiveRun {
        counter++
    }
    println("Counter = ${counter}")
}

suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
    val jobs = List(1000) {
        launch {
            repeat(1000) { action() }
        }
    }
    jobs.forEach { it.join() }
}