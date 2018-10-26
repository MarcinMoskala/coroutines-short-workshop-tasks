package examples.new3

import examples.massiveRun
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.runBlocking
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