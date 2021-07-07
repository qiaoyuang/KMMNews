package com.qiaoyuang.kmmnews.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> switchToIO(block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.IO, block)

actual suspend inline fun <reified R> doIO(crossinline block: () -> R): R =
    switchToIO {
        block()
    }

actual suspend inline fun
        <reified P0, reified R>
        doIO(p0: P0, crossinline block: (P0) -> R): R =
    switchToIO {
        block(p0)
    }

actual suspend inline fun
        <reified P0, reified P1, reified R>
        doIO(p0: P0, p1: P1, crossinline block: (P0, P1) -> R): R =
    switchToIO {
        block(p0, p1)
    }


actual suspend inline fun
        <reified P0, reified P1, reified P2, reified R>
        doIO(p0: P0, p1: P1, p2: P2, crossinline block: (P0, P1, P2) -> R): R =
    switchToIO {
        block(p0, p1, p2)
    }

actual suspend inline fun
        <reified P0, reified P1, reified P2, reified P3, reified R>
        doIO(p0: P0, p1: P1, p2: P2, p3: P3, crossinline block: (P0, P1, P2, P3) -> R): R =
    switchToIO {
        block(p0, p1, p2, p3)
    }

actual suspend inline fun
        <reified P0, reified P1, reified P2, reified P3, reified P4, reified R>
        doIO(p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, crossinline block: (P0, P1, P2, P3, P4) -> R): R =
    switchToIO {
        block(p0, p1, p2, p3, p4)
    }

actual suspend inline fun
        <reified P0, reified P1, reified P2, reified P3, reified P4, reified P5, reified R>
        doIO(p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, crossinline block: (P0, P1, P2, P3, P4, P5) -> R): R =
    switchToIO {
        block(p0, p1, p2, p3, p4, p5)
    }

