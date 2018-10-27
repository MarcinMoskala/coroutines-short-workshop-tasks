package examples

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.coroutineScope
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

suspend fun makeAsyncCalculationsInCoroutineScope(): String = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    "The answer is ${one.await() + two.await()}"
}

fun main(args: Array<String>) = runBlocking {
    val value = makeAsyncCalculationsInCoroutineScope()
    println(value)
}