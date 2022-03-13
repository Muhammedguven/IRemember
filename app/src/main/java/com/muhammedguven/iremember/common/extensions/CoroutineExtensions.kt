package com.muhammedguven.iremember.common.extensions

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlin.coroutines.cancellation.CancellationException

fun <T1, T2, T3, R> Flow<T1>.zip(
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    transform: suspend (T1, T2, T3) -> R
): Flow<R> = channelFlow {

    val first: ReceiveChannel<T1> = produce {
        this@zip.collect {
            channel.send(it)
        }
    }

    val second: ReceiveChannel<T2> = produce {
        flow2.collect {
            channel.send(it)
        }
    }

    val third: ReceiveChannel<T3> = produce {
        flow3.collect {
            channel.send(it)
        }
    }

    (second as SendChannel<*>).invokeOnClose {
        if (!first.isClosedForReceive) first.cancel(MyFlowException())
        if (!third.isClosedForReceive) third.cancel(MyFlowException())
    }

    (third as SendChannel<*>).invokeOnClose {
        if (!first.isClosedForReceive) first.cancel(MyFlowException())
        if (!second.isClosedForReceive) second.cancel(MyFlowException())
    }

    val otherIterator = second.iterator()
    val anotherIterator = third.iterator()

    try {
        first.consumeEach { value ->
            if (!otherIterator.hasNext() || !anotherIterator.hasNext()) {
                return@consumeEach
            }
            send(transform(value, otherIterator.next(), anotherIterator.next()))
        }
    } catch (e: MyFlowException) {
        // complete
    } finally {
        if (!second.isClosedForReceive) second.cancel(MyFlowException())
        if (!third.isClosedForReceive) third.cancel(MyFlowException())
    }
}

class MyFlowException : CancellationException()