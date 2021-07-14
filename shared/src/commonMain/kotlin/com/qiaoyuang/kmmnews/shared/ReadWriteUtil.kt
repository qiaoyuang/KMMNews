package com.qiaoyuang.kmmnews.shared

import kotlin.native.concurrent.ThreadLocal

/**
 * 读写 KV 数据
 */

@ThreadLocal
object ReadWriteUtil {

    private val mmkv = defaultMMKV()

    fun writeAll() = with(mmkv) {
        encode("int", 99)
        encode("long", 208L)
        encode("string", "Hello World")
        encode("float", 6.28f)
        encode("double", 3.1415926)
        println("写入完毕！！！")
    }

    fun readAndPrintAll() = with(mmkv) {
        println(decodeInt("int", 0))
        println(decodeLong("long", 0L))
        println(decodeString("string", "default"))
        println(decodeFloat("float", 0f))
        println(decodeDouble("double", 0.0))
    }

}