package com.qiaoyuang.kmmnews.shared

import com.qiaoyuang.kmmnews.shared.data.NewsContent
import com.qiaoyuang.kmmnews.shared.data.NewsSummary
import io.ktor.client.request.*

/**
 * 网络请求管理器
 * @author yaqiao
 */

object NetworkRequest {

    private const val BASE_URL = "http://localhost:8080/news"

    suspend fun getNewsSummaryList(): List<NewsSummary>? = try {
        CLIENT.get(BASE_URL)
    } catch (e: Exception) {
        println(e.message)
        null
    }

    suspend fun getNewsContent(newsSummary: NewsSummary): NewsContent? = try {
        CLIENT.post(BASE_URL) {
            headers["Content-Type"] = "application/json"
            body = newsSummary
        }
    } catch (e: Exception) {
        println(e.message)
        null
    }

}