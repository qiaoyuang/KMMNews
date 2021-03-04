package com.qiaoyuang.kmmnews.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.qiaoyuang.kmmnews.shared.NetworkRequest
import com.qiaoyuang.kmmnews.shared.data.NewsSummary
import kotlinx.coroutines.launch

/**
 * 新闻内容
 */

class NewsContentActivity : AppCompatActivity() {

    companion object {
        const val KEY_NEWS_SUMMARY = "KeyNewsSummary"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val tvSummary = findViewById<TextView>(R.id.tv_summary)
        val ivNews = findViewById<ImageView>(R.id.iv_news)
        val tvContent = findViewById<TextView>(R.id.tv_content)
        val tvEditor = findViewById<TextView>(R.id.tv_editor)
        val tvDate = findViewById<TextView>(R.id.tv_date)
        intent.getParcelableExtra<NewsSummary>(KEY_NEWS_SUMMARY)?.let {
            lifecycleScope.launch {
                NetworkRequest.getNewsContent(it)?.run {
                    tvTitle.text = title
                    tvSummary.text = summary
                    ivNews.loadURL(imageUrl, lifecycle)
                    tvContent.text = content
                    tvEditor.text = editor
                    tvDate.text = date
                }
            }
        }
    }

}