package examples

import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking<Unit> {
    supervisorScope {
        launch(CoroutineExceptionHandler { _, _ ->  }) {
            delay(1000)
            throw AssertionError("Cancelled")
        }
        launch {
            delay(2000)
            println("AAA")
        }
    }
}