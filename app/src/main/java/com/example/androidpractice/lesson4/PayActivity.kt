package com.example.androidpractice.lesson4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.androidpractice.R

class PayActivity: Activity() {
    lateinit var mChargePriceEditText: EditText
    lateinit var mChargeBtn: Button
    lateinit var mChargeCancelBtn: Button

    val TAG = "PayActivity"
    val SUCCESS_CODE = 2
    val CANCEL_CODE = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        initView()
        initListener()
    }

    private fun initListener() {
        mChargeBtn.setOnClickListener { handleCharge() }
        mChargeCancelBtn.setOnClickListener { handleCancel() }
    }

    private fun handleCancel() {
        // 回傳 取消 的訊息
        val intent = Intent()
        intent.putExtra("resultContent", "You cancelled!")
        setResult(CANCEL_CODE, intent)

        // 要手動取消這個 activity
        this.finish()
    }

    private fun handleCharge() {
        val priceText = mChargePriceEditText.text.toString().trim()
        if (TextUtils.isEmpty(priceText)) {
            Toast.makeText(this, "請輸入充值金額", Toast.LENGTH_SHORT).show()
            return
        }

        // 連接遠端進行加值...

        // 設置返回值: setResult()
        // setResult(): 可只回傳一個值；也可以回傳兩個值 (第二個值放 intent)
        // 如果沒有要回傳的資料，單純要通知重返，可選擇前者；需要回傳資料就選後者
        val intent = Intent()
        intent.putExtra("resultContent", "Success!")
        setResult(SUCCESS_CODE, intent)

        // 要手動取消這個 activity
        this.finish()
    }

    private fun initView() {
        mChargePriceEditText = this.findViewById(R.id.charge_price)
        mChargeBtn = this.findViewById(R.id.charge_btn)
        mChargeCancelBtn = this.findViewById(R.id.charge_cancel_btn)
    }
}
