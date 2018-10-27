package examples.n1

import examples.massiveRun
import kotlinx.coroutines.experimental.*
import java.util.concurrent.atomic.AtomicInteger

private var counter = AtomicInteger()

fun main(args: Array<String>) = runBlocking {
    GlobalScope.massiveRun {
        counter.incrementAndGet()
    }
    println("Counter = ${counter.get()}")
}
