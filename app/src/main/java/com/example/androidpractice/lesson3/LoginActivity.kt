package com.example.androidpractice.lesson3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.androidpractice.R

class LoginActivity: Activity() {
    lateinit var mUsernameEditText: EditText
    lateinit var mPasswordEditText: EditText
    lateinit var mRegisterButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        initListener()
    }

    private fun initListener() {
        mRegisterButton.setOnClickListener {
            // handle register event
            handleRegister()
        }
    }

    private fun handleRegister() {
        val usernameText = mUsernameEditText.text.toString().trim()
        val passwordText = mPasswordEditText.text.toString().trim()

        if (TextUtils.isEmpty(usernameText) || TextUtils.isEmpty(passwordText)) {
            Toast.makeText(this, "輸入值不能為空。", Toast.LENGTH_SHORT).show()
            return
        }

        // 跳轉到 RegisterResultActivity
        val intent = Intent(this, RegisterResultActivity::class.java)
        intent.putExtra("usernameKey", usernameText)
        intent.putExtra("passwordKey", passwordText)
        startActivity(intent)

        // 結束這個 activity，按下返回時才不會又回來這裡
        this.finish()
    }

    private fun initView() {
        mUsernameEditText = this.findViewById(R.id.username)
        mPasswordEditText = this.findViewById(R.id.password)
        mRegisterButton = this.findViewById(R.id.register_btn)
    }
}