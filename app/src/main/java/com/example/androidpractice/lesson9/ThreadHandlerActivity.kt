package com.example.androidpractice.lesson9

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.androidpractice.R
import java.lang.Exception

/* 目標：學習 Handler.post() 的使用方式
*  post() 裡面的參數放 Runnable，他會在 UI-thread 上運作
*  所以只能放 UI 的修改；不放長時間的邏輯執行
*  說明：模擬上傳文件功能，上傳過程所拋出的訊息顯示在畫面上 (例如：目前進度 85% )
* */
class ThreadHandlerActivity: Activity() {
    lateinit var mProgressBar: ProgressBar
    lateinit var mTextView: TextView
    lateinit var mUploadBtn: Button
    lateinit var mHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_handler)

        initView()
        initListener()
    }

    private fun initListener() {
        mUploadBtn.setOnClickListener {
            // 在這裡執行的項目是在 main-thread 執行，所以可以操作 UI
            mProgressBar.visibility = View.VISIBLE
            mUploadBtn.isEnabled = false

            // 處理上傳功能 (mock)
            // 此函數執行的 thread 為異步執行
            // 寫在他下面的項目會被優先排程，因此會先被執行
            handleUpload()

            // 例如在這裡寫了這個，不會在 thread 結束之後才執行
            // 而是比 Thread 優先執行
            // mUploadBtn.isEnabled = true
        }
    }

    private fun handleUpload() {
        var idx = 0

        // 開啟一個新的 thread 執行後台內容
        // 在這裡不能進行 UI 操作
        Thread(Runnable {
            while(idx < 100) {
                idx += 1

                // 切換到 UI-thread 執行 UI 畫面操作
                mHandler.post {
                    mProgressBar.progress = idx
                    mTextView.text = idx.toString() + "/" + mProgressBar.max.toString()
                }

                try {
                    Thread.sleep(40)
                } catch (e: Exception) {}
            }

            // 上傳結束，畫面回到初始狀態
            // 記得要用 post 切換到 UI-thread 才行
            mHandler.post{
                mProgressBar.visibility = View.INVISIBLE
                mUploadBtn.isEnabled = true
                mTextView.text = "finished!"
            }

            /* 備註：
            *  我發現 Visible & InVisible 的切換，不用一定得在 UI thread 執行
            *  可以直接在 Thread 裡面操作
            *  但 isEnabled 的切換還是得在 UI-thread 執行才行
            * */
        }).start()
    }

    private fun initView() {
        mProgressBar = this.findViewById(R.id.progress_bar)
        mTextView = this.findViewById(R.id.text_view)
        mUploadBtn = this.findViewById(R.id.upload_btn)

        mHandler = Handler()
    }
}