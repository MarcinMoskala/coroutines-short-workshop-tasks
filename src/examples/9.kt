@file:Suppress("RedundantSuspendModifier", "unused", "DEPRECATION", "UNUSED_PARAMETER")

package examples

import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext

public interface ContinuationInterceptor : CoroutineContext.Element {

    public fun <T> interceptContinuation(
            continuation: Continuation<T>
    ): Continuation<T>

    public fun releaseInterceptedContinuation(
            continuation: Continuation<*>
    ) {  }

    companion object Key : CoroutineContext.Key<ContinuationInterceptor>
}