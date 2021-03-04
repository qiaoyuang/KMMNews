package com.qiaoyuang.kmmnews.shared.data

import com.qiaoyuang.kmmnews.shared.Parcelable
// import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * 新闻摘要
 * @author yaqiao
 */

// @Parcelize
@Serializable
data class NewsSummary(
    val id: String = "",
    val title: String = "",
    val summary: String = "",
    val date: String = "",
    val imageUrl: String = "",
) : Parcelable
