package examples

import kotlinx.coroutines.experimental.*
import kotlin.concurrent.thread

//fun examples.main(args: Array<String>) = runBlocking {
//    repeat(100_000) {
//        launch {
//            delay(1000L)
//            print(".")
//        }
//    }
//}

// No! Don't do it! Very bed idea on threads
fun main(args: Array<String>) {
    repeat(100_000) {
        thread {
            Thread.sleep(1000L)
            print(".")
        }
    }
}