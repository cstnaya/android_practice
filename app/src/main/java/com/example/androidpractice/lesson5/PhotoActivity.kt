package com.example.androidpractice.lesson5

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.androidpractice.R

class PhotoActivity: Activity() {
    lateinit var mPhotoResult: ImageView
    lateinit var mTakePhotoBtn: ImageView
    val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        initView()
        initListener()
    }

    private fun initListener() {
        mTakePhotoBtn.setOnClickListener {
            // 要跳轉到系統的相機介面，用 startActivityForResult()
            val intent = Intent()

            // 使用隱式意圖 (action 要去對方原始碼查詢)
            intent.setAction("android.media.action.IMAGE_CAPTURE")
            intent.addCategory(Intent.CATEGORY_DEFAULT)

            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    private fun initView() {
        mPhotoResult = this.findViewById(R.id.photo_result)
        mTakePhotoBtn = this.findViewById(R.id.take_photo)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            // 去原始碼裡面查 對方 setResult() 當中的 resultCode 是多少
            if (resultCode == Activity.RESULT_OK) {
                // 成功返回

                // 去原始碼查對方送過來的東西 key 是什麼，類別是什麼
                val result = data?.getParcelableExtra<Bitmap>("data")

                if (result != null) {
                    // 再把拿到的圖片顯示出來
                    mPhotoResult.setImageBitmap(result)
                }

            }  else if (resultCode == Activity.RESULT_CANCELED) {
                // 被取消
                Toast.makeText(this, "對不起，您取消了拍照", Toast.LENGTH_SHORT).show()
            }
        }
    }
}