@file:Suppress("RedundantSuspendModifier", "unused", "DEPRECATION", "UNUSED_PARAMETER")

package examples

import kotlinx.coroutines.experimental.*

fun main(args: Array<String>) = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    val job = GlobalScope.launch(handler) {
        throw AssertionError()
    }
    job.join() // Caught java.lang.AssertionError
}