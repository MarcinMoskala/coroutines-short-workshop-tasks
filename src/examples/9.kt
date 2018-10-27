package examples

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.*

suspend fun makeAsyncCalculations(): String {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    return "The answer is ${one.await() + two.await()}"
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000)
    println("I am done")
    return 1
}

val random = Random()

suspend fun doSomethingUsefulTwo(): Int {
    delay(100)
    if (random.nextBoolean()) throw Error() else return 2
}

fun main(args: Array<String>) = runBlocking {
    val value = makeAsyncCalculations()
    println(value)
}