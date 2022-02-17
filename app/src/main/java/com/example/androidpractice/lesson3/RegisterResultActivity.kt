package com.example.androidpractice.lesson3

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.example.androidpractice.R

class RegisterResultActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_result)

        val intent = intent
        if (intent != null) {
            val username = intent.getStringExtra("usernameKey")
            val welcomeTextView: TextView = this.findViewById(R.id.welcome)
            welcomeTextView.setText("歡迎你，$username！")
        }
    }
}
