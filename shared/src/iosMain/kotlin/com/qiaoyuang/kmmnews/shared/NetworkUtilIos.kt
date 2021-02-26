package com.qiaoyuang.kmmnews.shared

import io.ktor.client.*
import io.ktor.client.engine.ios.*

/**
 * 与网络、JSON 解析等相关的一些配置
 * @author yaqiao
 */

internal actual val CLIENT = nsUrlSession()

private fun nsUrlSession(): HttpClient = HttpClient(Ios) {
    configJson()
    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}