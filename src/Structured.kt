import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.sync.withLock
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

// We have a worker who makes machines every 800ms as long as there is less than 5 of them
// Every machine produces a code using `produce` function every second. It saves this code to shared space. In case of an error, it ends working.
// We have a single manager that once a 2 seconds takes all produced codes and prints them all. After 5 times it ends all jobs (including machines and worker).

var codes = listOf<String>()
val mutex = Mutex()

fun main(args: Array<String>) = runBlocking<Unit> {
    val job = worker()
    repeat(5) {
        delay(2000)
        mutex.withLock {
            println("Codes are $codes")
            codes = emptyList()
        }
    }
    println("Time to finish")
    job.cancel()
}

private fun CoroutineScope.worker() = launch {
    val machinesNum = AtomicInteger(0)
    while (true) {
        if (machinesNum.get() < 5) {
            machine(machinesNum)
            println("Machine produced")
        }
        delay(800)
    }
}

private fun CoroutineScope.machine(machinesNum: AtomicInteger) {
    launch {
        try {
            while (true) {
                delay(1000)
                val code = produce()
                println("I produced code $code")
                mutex.withLock {
                    codes += code
                }
            }
        } catch (e: RandomError) {
            println("Machine is broken")
            machinesNum.decrementAndGet()
        }
    }
}

class RandomError() : Throwable()

private val letters = ('a'..'z') + ('0'..'9')
private val random = Random()

private fun produce(): String = when (random.nextInt(8)) {
    0 -> throw RandomError()
    else -> (1..5).map { letters[random.nextInt(letters.size)] }.joinToString(separator = "")
}