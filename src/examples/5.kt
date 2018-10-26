@file:Suppress("RedundantSuspendModifier", "unused", "DEPRECATION", "UNUSED_PARAMETER")

package examples

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.actor
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking {
    val job = Job()
    launch(job) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel()
    job.join()
    println("main: Now I can quit.")
}