package com.qiaoyuang.kmmnews.shared

/**
 * 双端统一的并行 CPU 密集型计算 API
 */

// 无参版本
expect suspend inline fun <reified R> calculateOnBackground(crossinline block: () -> R): R

// 单参数版本
expect suspend inline fun
        <reified P0, reified R>
        calculateOnBackground(p0: P0, crossinline block: (P0) -> R): R

// 双参数版本
expect suspend inline fun
        <reified P0, reified P1, reified R>
        calculateOnBackground(p0: P0, p1: P1, crossinline block: (P0, P1) -> R): R

// 3 参数版本
expect suspend inline fun
        <reified P0, reified P1, reified P2, reified R>
        calculateOnBackground(p0: P0, p1: P1, p2: P2, crossinline block: (P0, P1, P2) -> R): R

// 4 参数版本
expect suspend inline fun
        <reified P0, reified P1, reified P2, reified P3, reified R>
        calculateOnBackground(p0: P0, p1: P1, p2: P2, p3: P3, crossinline block: (P0, P1, P2, P3) -> R): R

// 5 参数版本
expect suspend inline fun
        <reified P0, reified P1, reified P2, reified P3, reified P4, reified R>
        calculateOnBackground(p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, crossinline block: (P0, P1, P2, P3, P4) -> R): R

// 6 参数版本
expect suspend inline fun
        <reified P0, reified P1, reified P2, reified P3, reified P4, reified P5, reified R>
        calculateOnBackground(p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, crossinline block: (P0, P1, P2, P3, P4, P5) -> R): R