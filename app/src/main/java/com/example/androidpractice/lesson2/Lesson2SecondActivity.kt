package com.example.androidpractice.lesson2

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.androidpractice.R

class Lesson2SecondActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent = intent
        if (intent != null) {
            /* 在此獲取 parcelable value */
            val user = intent.getParcelableExtra<User>("userKey")
            if (user != null) {
                Toast.makeText(this, "username: ${user.name}, age: ${user.age}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}