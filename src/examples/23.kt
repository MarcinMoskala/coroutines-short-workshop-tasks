package examples.new1

import examples.massiveRun
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.atomic.AtomicInteger

private var counter = AtomicInteger()

fun main(args: Array<String>) = runBlocking<Unit> {
    GlobalScope.massiveRun {
        counter.incrementAndGet()
    }
    println("Counter = ${counter.get()}")
}