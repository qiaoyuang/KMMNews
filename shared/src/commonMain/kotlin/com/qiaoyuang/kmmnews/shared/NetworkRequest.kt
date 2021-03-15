package com.qiaoyuang.kmmnews.shared

import com.qiaoyuang.kmmnews.shared.data.NewsContent
import com.qiaoyuang.kmmnews.shared.data.NewsSummary
import io.ktor.client.request.*
import io.ktor.client.utils.*

/**
 * 网络请求管理器
 */

object NetworkRequest {

    private const val URL_GET_NEWS_LIST = "http://192.168.1.3:8080/news"

    suspend fun getNewsSummaryList(): List<NewsSummary>? = try {
        CLIENT.get(URL_GET_NEWS_LIST)
    } catch (e: Exception) {
        println(e.message)
        null
    }

    private const val URL_GET_NEWS_CONTENT = "http://192.168.1.3:8080/news"

    suspend fun getNewsContent(newsSummary: NewsSummary): NewsContent? = try {
        CLIENT.post(URL_GET_NEWS_CONTENT) {
            headers["Content-Type"] = "application/json"
            body = newsSummary
        }
    } catch (e: Exception) {
        println(e.message)
        null
    }

}