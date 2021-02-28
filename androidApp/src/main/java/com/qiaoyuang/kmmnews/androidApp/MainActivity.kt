package com.qiaoyuang.kmmnews.androidApp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qiaoyuang.kmmnews.shared.data.NewsSummary
import kotlinx.coroutines.launch

/**
 * 首页（新闻列表）
 */

class MainActivity : AppCompatActivity() {

    private companion object {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_news).apply {
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        lifecycleScope.launch {

        }
    }

    private class NewsAdapter(val context: Context, val newsSummaryList: List<NewsSummary>) : RecyclerView.Adapter<NewsViewHolder>() {

        override fun getItemCount(): Int = newsSummaryList.size

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) = with(holder) {
            val newsSummary = newsSummaryList[position]
            tvTitle.text = newsSummary.title
            tvSummary.text = newsSummary.summary
            tvDate.text = newsSummary.date
            itemView.setOnClickListener {
                // 启动新闻详情页
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