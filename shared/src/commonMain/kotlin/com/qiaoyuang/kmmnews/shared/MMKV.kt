package com.qiaoyuang.kmmnews.shared

/**
 * MMKV common 层抽象
 */

expect class MMKV

expect fun defaultMMKV(): MMKV

/**
 * 存值
 */

expect fun MMKV.encode(key: String, value: Int): Boolean

expect fun MMKV.encode(key: String, value: String): Boolean

expect fun MMKV.encode(key: String, value: Long): Boolean

expect fun MMKV.encode(key: String, value: Float): Boolean

expect fun MMKV.encode(key: String, value: Double): Boolean

expect fun MMKV.encode(key: String, value: ByteArray): Boolean

/**
 * 取值
 */

expect fun MMKV.decodeInt(key: String, default: Int): Int

expect fun MMKV.decodeString(key: String, default: String): String

expect fun MMKV.decodeLong(key: String, default: Long): Long

expect fun MMKV.decodeFloat(key: String, default: Float): Float

expect fun MMKV.decodeDouble(key: String, default: Double): Double

expect fun MMKV.decodeByteArray(key: String, default: ByteArray): ByteArray