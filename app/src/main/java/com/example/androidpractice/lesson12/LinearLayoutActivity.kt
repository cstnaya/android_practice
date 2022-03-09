package com.example.androidpractice.lesson12

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.androidpractice.R

class LinearLayoutActivity: Activity() {
    lateinit var mBtn_rela: Button
    lateinit var mBtn_calc: Button

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear)
        initView()
        initListener()
    }

    private fun initListener() {
        mBtn_rela.setOnClickListener {
            val intent = Intent(this, RelativeLayoutActivity::class.java)
            startActivity(intent)
        }

        mBtn_calc.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initView() {
        mBtn_rela = this.findViewById(R.id.goto_relative)
        mBtn_calc = this.findViewById(R.id.goto_calc)
    }

}