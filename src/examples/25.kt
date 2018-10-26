@file:Suppress("RedundantSuspendModifier", "unused", "DEPRECATION", "UNUSED_PARAMETER")

package examples.new

import examples.massiveRun
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.sync.withLock

private val mutex = Mutex()
private var counter = 0

fun main(args: Array<String>) = runBlocking {
    GlobalScope.massiveRun {
        mutex.withLock {
            counter++
        }
    }
    println("Counter = $counter")
}