package examples

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
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