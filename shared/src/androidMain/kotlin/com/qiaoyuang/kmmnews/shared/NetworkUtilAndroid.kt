package com.qiaoyuang.kmmnews.shared

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

/**
 * 与网络、JSON 解析等相关的一些配置
 * @author yaqiao
 */

internal actual val CLIENT = okHttp()

private fun okHttp(): HttpClient = HttpClient(OkHttp) {
    configJson()
}