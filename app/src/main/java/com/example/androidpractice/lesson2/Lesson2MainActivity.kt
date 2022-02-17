package com.example.androidpractice.lesson2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.androidpractice.R

/* Lesson2: 傳遞 Parcelable value 到另一個 activity */

class Lesson2MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)
    }

    /* 目標：點擊跳轉按鈕後把 user 訊息傳給下一個 activity */
    /* 注意：如果是寫在 layout.xml 的 onClick function，必須設為 public */
    fun deliverObject (view: View) {
        /* 1. 如同前面，創立一個 intent */
        val intent = Intent(this, Lesson2SecondActivity::class.java)

        /* 2. 創建一個 user 對象，並裝載進 intent */
        /* 創建的對象 (User) 必須要是 Parcelable，因為 putExtra() 第二個參數只接收實作了 parcelable 或 serialize 的對象 */
        val user = User("Andy", 18)
        intent.putExtra("userKey", user)

        startActivity(intent)
    }
}