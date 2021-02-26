package com.qiaoyuang.kmmnews.shared.data

import com.qiaoyuang.kmmnews.shared.CLIENT
import io.ktor.client.request.*

/**
 * 网络请求管理器
 * @author yaqiao
 */

object NetworkManager {

    suspend fun getNewsList(): List<NewsSummary> = CLIENT.get("1.1.1.2")

    suspend fun getNewsContent(request: NewsSummary): NewsContent = CLIENT.post("1.1.1.2") {
        body = request
    }

}