import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>): Unit = runBlocking {
    println("Started!")
    test()
    println("Done.")
}

suspend fun test() {
    delay(1000)
}