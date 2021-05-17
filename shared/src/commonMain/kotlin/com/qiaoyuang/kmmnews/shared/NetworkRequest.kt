package com.qiaoyuang.kmmnews.shared

import com.qiaoyuang.kmmnews.shared.data.NewsContent
import com.qiaoyuang.kmmnews.shared.data.NewsSummary
import io.ktor.client.request.*

/**
 * 网络请求管理器
 * @author yaqiao
 */

object NetworkRequest {

    private const val URL_GET_NEWS_LIST = "https://demo.ktor.tips/news"

    suspend fun getNewsSummaryList(): List<NewsSummary>? = try {
        CLIENT.get(URL_GET_NEWS_LIST)
    } catch (e: Exception) {
        println(e.message)
        null
    }

    private const val URL_GET_NEWS_CONTENT = "https://demo.ktor.tips/news"

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