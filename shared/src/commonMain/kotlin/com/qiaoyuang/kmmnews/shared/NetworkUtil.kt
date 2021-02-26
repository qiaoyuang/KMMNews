package com.qiaoyuang.kmmnews.shared

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json

/**
 * 与网络、JSON 解析等相关的一些配置
 * @author yaqiao
 */

internal fun <T : HttpClientEngineConfig> HttpClientConfig<T>.configJson() =
    install(JsonFeature) {
        serializer = KotlinxSerializer(KJson)
    }

val KJson = Json {
    isLenient = true
    ignoreUnknownKeys = true
    allowSpecialFloatingPointValues = true
    useArrayPolymorphism = true
}

internal expect val CLIENT: HttpClient