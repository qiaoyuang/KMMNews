@file:OptIn(ExperimentalUnsignedTypes::class)

package com.qiaoyuang.kmmnews.shared

import com.tencent.mmkv.MMKV
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.allocArrayOf
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.create
import platform.posix.memcpy

/**
 * MMKV iOS 层封装
 */

actual typealias MMKV = MMKV

actual fun defaultMMKV(): MMKV = MMKV.defaultMMKV()!!

fun MMKVInitialize() = MMKV.initialize()

/**
 * 存值
 */

actual fun MMKV.encode(key: String, value: Int): Boolean = setInt32(value, key)

actual fun MMKV.encode(key: String, value: String): Boolean = setString(value, key)

actual fun MMKV.encode(key: String, value: Long): Boolean = setInt64(value, key)

actual fun MMKV.encode(key: String, value: Float): Boolean = setFloat(value, key)

actual fun MMKV.encode(key: String, value: Double): Boolean = setDouble(value, key)

actual fun MMKV.encode(key: String, value: ByteArray): Boolean = setData(value.toNSData(), key)

/**
 * 取值
 */

actual fun MMKV.decodeInt(key: String, default: Int): Int = getInt32ForKey(key, default)

actual fun MMKV.decodeString(key: String, default: String): String = getStringForKey(key, default) ?: ""

actual fun MMKV.decodeLong(key: String, default: Long): Long = getInt64ForKey(key, default)

actual fun MMKV.decodeFloat(key: String, default: Float): Float = getFloatForKey(key, default)

actual fun MMKV.decodeDouble(key: String, default: Double): Double = getDoubleForKey(key, default)

actual fun MMKV.decodeByteArray(key: String, default: ByteArray): ByteArray = getDataForKey(key, default.toNSData())?.toByteArray() ?: ByteArray(0)

/**
 * 转换函数
 */

internal fun NSData.toByteArray(): ByteArray = ByteArray(this@toByteArray.length.toInt()).apply {
    usePinned {
        memcpy(it.addressOf(0), this@toByteArray.bytes, this@toByteArray.length)
    }
}

internal fun ByteArray.toNSData(): NSData = memScoped {
    NSData.create(bytes = allocArrayOf(this@toNSData),
        length = this@toNSData.size.toULong())
}