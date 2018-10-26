@file:Suppress("RedundantSuspendModifier", "unused", "DEPRECATION", "UNUSED_PARAMETER")

package examples

import kotlinx.coroutines.experimental.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking {
    val request = launch {
        repeat(3) { i ->
            launch  {
                delay((i + 1) * 200L)
                println("Coroutine $i is done")
            }
        }
        println("request: I'm done and I don't explicitly join my children that are still active")
    }
    request.join()
    println("Now processing of the request is complete")
}