package com.example.androidpractice.lesson1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.example.androidpractice.R

/* 創立一個新的 activity，要記得去 Manifest 裡面註冊 (顯式註冊) */
class HomeActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val info: TextView = this.findViewById(R.id.info)

        /* 由於 MainActivity 是透過 intent 裝載資料，所以也要從 intent 當中獲取資料 */
        val intent: Intent = intent

        val account: String? = intent.getStringExtra("account")
        val password: String? = intent.getStringExtra("password")

        info.setText("帳號： $account, 密碼： $password")

    }
}