@file:Suppress("RedundantSuspendModifier", "unused", "DEPRECATION", "UNUSED_PARAMETER")

package examples.new3

import examples.massiveRun
import kotlinx.coroutines.experimental.*
import java.util.concurrent.atomic.AtomicInteger

private var counter = AtomicInteger()

fun main(args: Array<String>) = runBlocking<Unit> {
    GlobalScope.massiveRun {
        counter.incrementAndGet()
    }
    println("Counter = ${counter.get()}")
}