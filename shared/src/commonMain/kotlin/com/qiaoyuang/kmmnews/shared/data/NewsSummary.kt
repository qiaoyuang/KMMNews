package com.qiaoyuang.kmmnews.shared.data

import kotlinx.serialization.Serializable

/**
 * 新闻摘要
 * @author yaqiao
 */

@Serializable
data class NewsSummary(
    val id: String = "",
    val title: String = "",
    val summary: String = "",
    val date: String = "",
    val imageUrl: String = "",
)
