package com.example.androidpractice.lesson4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.androidpractice.R
import com.example.androidpractice.lesson1.HomeActivity
import org.w3c.dom.Text

class MainActivity: Activity() {
    lateinit var mButton: Button
    lateinit var mPayResult: TextView

    val TAG = "MainActivity"
    val REQUEST_CHARGE_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_4)

        initView()
        initListener()
    }

    private fun initListener() {
        mButton.setOnClickListener {
            // 設置點擊事件
            val intent: Intent = Intent(this, PayActivity::class.java)

            // 1. 使用 startActivityForResult() 代替平常用的 startActivity()
            // 第二個參數: requestCode，如果有回傳值要用這個 requestCode 辨別是針對這項點擊事件的回傳
            startActivityForResult(intent, REQUEST_CHARGE_CODE)
        }
    }

    private fun initView() {
        mButton = this.findViewById(R.id.recharge_btn)
        mPayResult = this.findViewById(R.id.pay_result)
    }

    /* 2. 返回的結果會傳到這裡，為了接收結果要 override onActivityResult() */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 透過 requestCode 判斷是來自哪裡的請求
        if (requestCode == REQUEST_CHARGE_CODE) {
            var resultContent: String? = ""
            if (resultCode == 2) {
                resultContent = data?.getStringExtra("resultContent")
            }
            else if (resultCode == 3) {
                resultContent = data?.getStringExtra("resultContent")
            }

            mPayResult.setText("$resultContent")
        }
    }
}