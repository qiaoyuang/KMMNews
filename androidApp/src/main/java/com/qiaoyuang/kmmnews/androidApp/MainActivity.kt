package com.qiaoyuang.kmmnews.androidApp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qiaoyuang.kmmnews.shared.NetworkRequest
import com.qiaoyuang.kmmnews.shared.data.NewsSummary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 首页（新闻列表）
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_news).apply {
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        lifecycleScope.launch(Dispatchers.IO) {
            NetworkRequest.getNewsSummaryList()?.let {
                withContext(Dispatchers.Main) {
                    val newsAdapter = NewsAdapter(this@MainActivity, it, lifecycle, lifecycleScope)
                    recyclerView.adapter = newsAdapter
                }
            }
        }
    }

    private class NewsAdapter(private val context: Context,
                              private val newsSummaryList: List<NewsSummary>,
                              private val lifecycle: Lifecycle,
                              private val coroutineScope: CoroutineScope) : RecyclerView.Adapter<NewsViewHolder>() {

        override fun getItemCount(): Int = newsSummaryList.size

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) = with(holder) {
            val newsSummary = newsSummaryList[position]
            tvTitle.text = newsSummary.title
            tvSummary.text = newsSummary.summary
            tvDate.text = newsSummary.date
            coroutineScope.launch {
                ivNews.loadURL(newsSummary.imageUrl, lifecycle)
            }
            itemView.setOnClickListener {
                // 启动新闻详情页
                val intent = Intent().apply {
                    putExtra(NewsContentActivity.KEY_NEWS_SUMMARY, newsSummary)
                }
                context.startActivity(intent)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
            NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.view_news_item, parent, false))
    }

    private class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)!!
        val tvSummary = itemView.findViewById<TextView>(R.id.tv_summary)!!
        val tvDate = itemView.findViewById<TextView>(R.id.tv_date)!!
        val ivNews = itemView.findViewById<ImageView>(R.id.iv_news)!!
    }

}