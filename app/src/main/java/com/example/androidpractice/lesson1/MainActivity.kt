package com.example.androidpractice.lesson1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.androidpractice.R

/* Lesson 1 */
/* Target: 透過【顯式意圖】傳遞資訊，並跳轉到另一個 activity */

class MainActivity : AppCompatActivity() {
    lateinit var mAccount: EditText
    lateinit var mPassword: EditText
    lateinit var mButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initListener()
    }

    private fun initListener() {
        mButton.setOnClickListener { handleLogin() }
    }

    private fun handleLogin() {
        val accountText: String = mAccount.text.toString().trim()
        val passwordText: String = mPassword.text.toString().trim()

        if (TextUtils.isEmpty(accountText) || TextUtils.isEmpty(passwordText)) {
            Toast.makeText(this, "輸入為空值", Toast.LENGTH_SHORT).show()
            return
        }

        /* 重點： 帳號及密碼不為空值，傳遞資料到另一個 activity */
        /* 1. 先創立一個 intent  */
        val intent: Intent = Intent(this, HomeActivity::class.java)

        /* 2. 裝載資料 */
        intent.putExtra("account", accountText)
        intent.putExtra("password", passwordText)

        /* 3. 再透過 startActivity() 跳轉 */
        startActivity(intent)
    }

    private fun initView() {
        mAccount = this.findViewById(R.id.account)
        mPassword = this.findViewById(R.id.password)
        mButton = this.findViewById(R.id.login)
    }
}