package ui

import kotlinx.coroutines.experimental.*
import org.junit.jupiter.api.Test
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.test.assertEquals

val UI = newSingleThreadContext("UIThread") // Normally it will be Dispatchers.Main

// TODO: Edit this class
abstract class BasePresenter(val onError: (Throwable) -> Unit = {}) {

    fun onDestroy() {
    }
}

@Suppress("FunctionName")
class SieveTests {

    class FakePresenter(
            private val jobInterceptor: (() -> Unit)? = null,
            onError: (Throwable) -> Unit = {}
    ) : BasePresenter(onError) {

        var cancelledJobs = 0

        fun onCreate() {
            launch {
                try {
                    delay(100)
                    jobInterceptor?.invoke()
                    delay(2000)
                } finally {
                    cancelledJobs += 1
                }
            }
            launch {
                try {
                    delay(100)
                    jobInterceptor?.invoke()
                    delay(2000)
                } finally {
                    cancelledJobs += 1
                }
            }
        }
    }

    @Test
    fun `onDestroy cancels all jobs`() = runBlocking {
        val presenter = FakePresenter()
        presenter.onCreate()
        delay(200)
        presenter.onDestroy()
        delay(200)
        assertEquals(2, presenter.cancelledJobs)
    }

    @Test
    fun `Coroutines run on main thread`() = runBlocking {
        var threads = listOf<Thread>()
        val presenter = FakePresenter(
                jobInterceptor = {
                    threads += Thread.currentThread()
                }
        )
        presenter.onCreate()
        delay(200)
        presenter.onDestroy()
        threads.forEach {
            assert(it.name.startsWith("UIThread")) { "We should switch to UI thread, and now we are on ${it.name}" }
        }
    }

    @Test
    fun `When a job throws an error, it is handled`(): Unit = runBlocking {
        val error = Error()
        var errors = listOf<Throwable>()
        val presenter = FakePresenter(
                jobInterceptor = { throw error },
                onError = { errors += it }
        )
        presenter.onCreate()
        delay(200)
        presenter.onDestroy()
        assertEquals(listOf(error, error), errors)
    }
}