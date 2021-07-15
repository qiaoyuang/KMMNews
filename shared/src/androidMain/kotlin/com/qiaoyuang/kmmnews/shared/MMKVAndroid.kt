package com.qiaoyuang.kmmnews.shared

import android.content.Context
import com.tencent.mmkv.MMKV

/**
 * MMKV Android 层封装
 */

actual typealias MMKV = MMKV

actual fun defaultMMKV(): MMKV = MMKV.defaultMMKV()

fun Context.MMKVInitialize() = MMKV.initialize(this)

/**
 * 存值
 */

actual fun MMKV.encode(key: String, value: Int): Boolean = encode(key, value)

actual fun MMKV.encode(key: String, value: String): Boolean = encode(key, value)

actual fun MMKV.encode(key: String, value: Long): Boolean = encode(key, value)

actual fun MMKV.encode(key: String, value: Float): Boolean = encode(key, value)

actual fun MMKV.encode(key: String, value: Double): Boolean = encode(key, value)

actual fun MMKV.encode(key: String, value: ByteArray): Boolean = encode(key, value)

/**
 * 取值
 */

actual fun MMKV.decodeInt(key: String, default: Int): Int = decodeInt(key, default)

actual fun MMKV.decodeString(key: String, default: String): String = decodeString(key, default) ?: ""

actual fun MMKV.decodeLong(key: String, default: Long): Long = decodeLong(key, default)

actual fun MMKV.decodeFloat(key: String, default: Float): Float = decodeFloat(key, default)

actual fun MMKV.decodeDouble(key: String, default: Double): Double = decodeDouble(key, default)

actual fun MMKV.decodeByteArray(key: String, default: ByteArray): ByteArray = decodeBytes(key, default) ?: ByteArray(0)

