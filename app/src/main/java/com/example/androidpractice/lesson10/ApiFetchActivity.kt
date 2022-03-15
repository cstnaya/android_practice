package com.example.androidpractice.lesson10

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice.R
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.JsonHttpResponseHandler
import com.squareup.picasso.Picasso
import cz.msebera.android.httpclient.Header
import java.nio.charset.Charset

class ApiFetchActivity: Activity() {
    val TAG = "ApiFetchActivity"

    lateinit var mData: List<Item>
    lateinit var mRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_fetch)

        // 1. use AsyncHttpClient to fetch json-data through INTERNET
        fetchData()
    }

    private fun fetchData() {
        // fetch key from local.properties
        val ai: ApplicationInfo = applicationContext.packageManager
                                    .getApplicationInfo(applicationContext.packageName,
                                                        PackageManager.GET_META_DATA)
        val key = ai.metaData["api_key"]

        val client = AsyncHttpClient()
        val url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=UUCVejYJMmo-B0GqZbEJpiPw&maxResult=100&key=${key}"
        client.get(url, object: AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                // 接收到的 raw data 需要先經過 UTF 解碼，否則無法識別
                val response = String(responseBody!!, Charsets.UTF_8)

                // 透過 Gson() 把資料從 string-json，轉型成 custom-class: MovieGson
                val myData = Gson().fromJson(response, MovieGson::class.java)
                mData = myData.items

                // 2. set and bind recycle view with data
                initRecycleView()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Toast.makeText(this@ApiFetchActivity, "API Fetch Failed!", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "ERROR from Fetch Call")
            }
        })
    }

    private fun initRecycleView() {
        // 把視覺 layout 的 recyclerView 和 class MyAdapter 連接

        mRecyclerView = this.findViewById(R.id.recycler_view)
        mRecyclerView.layoutManager = LinearLayoutManager(this@ApiFetchActivity)
        mRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        myAdapter = MyAdapter(this, mData)
        mRecyclerView.adapter = myAdapter
    }

    class MyAdapter(private val context: Context,
                    private val data: List<Item>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        inner class MyViewHolder (itemView: View): RecyclerView.ViewHolder (itemView) {
            private val title = itemView.findViewById<TextView>(R.id.text_title)
            private val desc = itemView.findViewById<TextView>(R.id.text_desc)
            private val author = itemView.findViewById<TextView>(R.id.text_author)
            private val pDate = itemView.findViewById<TextView>(R.id.text_publish)
            private val thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)

            fun bind (item: Item) {
                title.text = item.snippet?.title
                desc.text = item.snippet?.description
                author.text = item.snippet?.channelTitle
                pDate.text = item.snippet?.publishedAt

                val thumbnailUrl = item.snippet?.thumbnails?.medium?.url
                Picasso.with(context).load(thumbnailUrl).into(thumbnail)

                thumbnail.setOnClickListener{
                    val id = item.snippet?.resourceId?.videoId
                    val url = "https://www.youtube.com/watch?v=$id"

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            }
        }

        // 連接 xml 檔
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycle_item, parent, false)
            return MyViewHolder(view)
        }

        // 在此控制 單個 item
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val row = data[position]

            // bind 資料和 item
            holder.bind(row)
        }

        // 顯示總數量
        override fun getItemCount(): Int {
            return data.count()
        }
    }
}