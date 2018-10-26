@file:Suppress("RedundantSuspendModifier", "unused", "DEPRECATION", "UNUSED_PARAMETER")

package coroutines.examples

import kotlinx.coroutines.experimental.*

fun main(args: Array<String>) = runBlocking<Unit> {
    fun getThreadName() = Thread.currentThread().name
    launch {
        println("main runBlocking      : I'm working in thread ${getThreadName()}")
    }
    launch(Dispatchers.Unconfined) {
        println("Unconfined            : I'm working in thread ${getThreadName()}")
    }
    launch(Dispatchers.Default) {
        println("Default               : I'm working in thread ${getThreadName()}")
    }
    launch(newSingleThreadContext("MyOwnThread")) {
        println("newSingleThreadContext: I'm working in thread ${getThreadName()}")
    }
}