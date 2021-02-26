package com.qiaoyuang.kmmnews.shared.data

import kotlinx.serialization.Serializable

/**
 * 获取新闻的请求体与响应体
 * @author yaqiao
 */

@Serializable
data class NewsContent(
    val id: String = "",
    val title: String = "",
    val summary: String = "",
    val date: String = "",
    val imageUrl: String = "",
    val content: String = "",
    val editor: String = "",
)