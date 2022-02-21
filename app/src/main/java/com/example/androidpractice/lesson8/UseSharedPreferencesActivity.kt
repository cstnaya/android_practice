package com.example.androidpractice.lesson8

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Switch
import com.example.androidpractice.R

/* 如何用 sharedPreferences 保存數據
*  通常用於儲存設定值 */
class UseSharedPreferencesActivity: Activity() {
    lateinit var mSP: SharedPreferences
    lateinit var toggle_bar: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_sp)

        initView()
        initListener()
    }

    override fun onResume() {
        super.onResume()

        // 5. 獲得 sp 裡面的值
        val state: Boolean = mSP.getBoolean("state", false) // default 值

        // 6. 套用已經儲存的值
        toggle_bar.isChecked = state
    }

    private fun initListener() {
        toggle_bar.setOnCheckedChangeListener { buttonView, isChecked ->
            handleSaveInfo(isChecked)
        }
    }

    private fun handleSaveInfo(isChecked: Boolean) {
        // 2. 進入編輯模式
        val edit = mSP.edit()

        // 3. save data，這邊存 boolean
        // 可存 Boolean, String, Float, Int, ...
        edit.putBoolean("state", isChecked)

        // 4. 要提交編輯器
        edit.commit()
    }

    private fun initView() {
        toggle_bar = this.findViewById(R.id.toggle_bar)

        // 1. 拿到 sp
        mSP = this.getSharedPreferences("setting_info", MODE_PRIVATE)
    }
}