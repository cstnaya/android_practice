package com.example.androidpractice.lesson6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.androidpractice.R

class SecondActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_6)
    }

    public fun gotoFirst(view: View) {
        startActivity(Intent(this, FirstActivity::class.java))
    }

    public fun gotoSecond(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }
}