package com.qiaoyuang.kmmnews.shared

/**
 * 可序列化接口在 common 层的抽象
 */

expect interface Parcelable

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class Parcelize()