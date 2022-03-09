package com.example.androidpractice.lesson12

import android.app.Activity
import android.os.Bundle
import com.example.androidpractice.R

class RelativeLayoutActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative_parent)
//        setContentView(R.layout.activity_relative_sibling)
    }
}