@file:Suppress("RedundantSuspendModifier", "unused", "DEPRECATION", "UNUSED_PARAMETER")

package examples

import kotlinx.coroutines.experimental.*
import kotlin.system.measureTimeMillis

suspend fun doSomethingUsefulOne(): Int {
    delay(1000)
    return 1
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000)
    return 2
}

suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}