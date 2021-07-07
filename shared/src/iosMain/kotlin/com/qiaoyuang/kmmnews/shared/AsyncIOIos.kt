package com.qiaoyuang.kmmnews.shared

/**
 * 双端统一的异步 IO API
 */

// 无参版本
actual suspend inline fun <reified R> doIO(crossinline block: () -> R): R =
    calculateOnBackground(block)

// 单参数版本
actual suspend inline fun
        <reified P0, reified R>
        doIO(p0: P0, crossinline block: (P0) -> R): R =
    calculateOnBackground(p0, block)

// 双参数版本
actual suspend inline fun
        <reified P0, reified P1, reified R>
        doIO(p0: P0, p1: P1, crossinline block: (P0, P1) -> R): R =
    calculateOnBackground(p0, p1, block)

// 3 参数版本
actual suspend inline fun
        <reified P0, reified P1, reified P2, reified R>
        doIO(p0: P0, p1: P1, p2: P2, crossinline block: (P0, P1, P2) -> R): R =
    calculateOnBackground(p0, p1, p2, block)

// 4 参数版本
actual suspend inline fun
        <reified P0, reified P1, reified P2, reified P3, reified R>
        doIO(p0: P0, p1: P1, p2: P2, p3: P3, crossinline block: (P0, P1, P2, P3) -> R): R =
    calculateOnBackground(p0, p1, p2, p3, block)

// 5 参数版本
actual suspend inline fun
        <reified P0, reified P1, reified P2, reified P3, reified P4, reified R>
        doIO(p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, crossinline block: (P0, P1, P2, P3, P4) -> R): R =
    calculateOnBackground(p0, p1, p2, p3, p4, block)

// 6 参数版本
actual suspend inline fun
        <reified P0, reified P1, reified P2, reified P3, reified P4, reified P5, reified R>
        doIO(p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, crossinline block: (P0, P1, P2, P3, P4, P5) -> R): R =
    calculateOnBackground(p0, p1, p2, p3, p4, p5, block)