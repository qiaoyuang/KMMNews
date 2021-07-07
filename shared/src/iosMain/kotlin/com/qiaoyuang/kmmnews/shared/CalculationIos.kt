package com.qiaoyuang.kmmnews.shared

import platform.darwin.DISPATCH_QUEUE_PRIORITY_BACKGROUND
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_global_queue
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.native.concurrent.DetachedObjectGraph
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.attach
import kotlin.native.concurrent.freeze

/**
 * 双端统一的并行 CPU 密集型计算 API（iOS 实现）
 */

inline fun <T> T.wrapToDetachedObjectGraph(): DetachedObjectGraph<T> = DetachedObjectGraph(TransferMode.UNSAFE) { this }

@OptIn(ExperimentalUnsignedTypes::class)
actual suspend inline fun <reified R> calculateOnBackground(crossinline block: () -> R): R = suspendCoroutine { continuation ->
    val queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND.toLong() ,0)
    val continuationGraph = continuation.wrapToDetachedObjectGraph()
    dispatch_async(queue, { // 该 lambda 运行在子线程
        val resultGraph = block().wrapToDetachedObjectGraph()
        val tempContinuationGraph = continuationGraph.attach().wrapToDetachedObjectGraph()
        dispatch_async(dispatch_get_main_queue(), { // 该 lambda 运行在主线程，用于在主线程内调用 resume 函数恢复协程，避免 IncorrectDereferenceException
            tempContinuationGraph.attach().resume(resultGraph.attach())
        }.freeze())
    }.freeze())
}

actual suspend inline fun
        <reified P0, reified R>
        calculateOnBackground(p0: P0, crossinline block: (P0) -> R): R {
    val p0Graph = p0.wrapToDetachedObjectGraph()
    return calculateOnBackground {
        block(p0Graph.attach())
    }
}

actual suspend inline fun
        <reified P0, reified P1, reified R>
        calculateOnBackground(p0: P0, p1: P1, crossinline block: (P0, P1) -> R): R {
    val p1Graph = p1.wrapToDetachedObjectGraph()
    return calculateOnBackground(p0) {
        block(it, p1Graph.attach())
    }
}

actual suspend inline fun
        <reified P0, reified P1, reified P2, reified R>
        calculateOnBackground(p0: P0, p1: P1, p2: P2, crossinline block: (P0, P1, P2) -> R): R {
    val p2Graph = p2.wrapToDetachedObjectGraph()
    return calculateOnBackground(p0, p1) { _p0, _p1 ->
        block(_p0, _p1, p2Graph.attach())
    }
}

actual suspend inline fun
        <reified P0, reified P1, reified P2, reified P3, reified R>
        calculateOnBackground(p0: P0, p1: P1, p2: P2, p3: P3, crossinline block: (P0, P1, P2, P3) -> R): R {
    val p3Graph = p3.wrapToDetachedObjectGraph()
    return calculateOnBackground(p0, p1, p2) { _p0, _p1, _p2 ->
        block(_p0, _p1, _p2, p3Graph.attach())
    }
}

actual suspend inline fun
        <reified P0, reified P1, reified P2, reified P3, reified P4, reified R>
        calculateOnBackground(p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, crossinline block: (P0, P1, P2, P3, P4) -> R): R {
    val p4Graph = p4.wrapToDetachedObjectGraph()
    return calculateOnBackground(p0, p1, p2, p3) { _p0, _p1, _p2, _p3 ->
        block(_p0, _p1, _p2, _p3, p4Graph.attach())
    }
}

actual suspend inline fun
        <reified P0, reified P1, reified P2, reified P3, reified P4, reified P5, reified R>
        calculateOnBackground(p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, crossinline block: (P0, P1, P2, P3, P4, P5) -> R): R {
    val p5Graph = p5.wrapToDetachedObjectGraph()
    return calculateOnBackground(p0, p1, p2, p3, p4) { _p0, _p1, _p2, _p3, _p4 ->
        block(_p0, _p1, _p2, _p3, _p4, p5Graph.attach())
    }
}