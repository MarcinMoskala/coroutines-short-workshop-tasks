package examples

import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking<Unit> {
    val supervisor = SupervisorJob()
    with(CoroutineScope(coroutineContext + supervisor)) {
        launch(CoroutineExceptionHandler { _, _ ->  }) {
            delay(1000)
            throw AssertionError("Cancelled")
        }
        launch {
            delay(2000)
            println("AAA")
        }
    }
    supervisor.join()
}
