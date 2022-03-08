package com.example.androidpractice.lesson11

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.androidpractice.R
import org.w3c.dom.Text

/*
*  目標：從相機或是相簿選擇一張圖片上傳
* */
class CapturePhotoActivity: Activity() {
    val REQUEST_CAMERA = 1
    val REQUEST_GALLERY = 2

    lateinit var mBtnUpload: Button
    lateinit var mPhotoResult: ImageView
    lateinit var mDialog: Dialog
    lateinit var mCamera: TextView
    lateinit var mGallery: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture_photo)

        initView()
        initListener()
    }

    private fun initListener() {
        mBtnUpload.setOnClickListener {
            // 點擊上傳按鈕 -> 顯示 dialog
            mDialog.show()
        }

        // 選擇 camera
        mCamera.setOnClickListener {
            mDialog.dismiss()

            // open camera
            val intent = Intent()
            intent.setAction("android.media.action.IMAGE_CAPTURE")
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            startActivityForResult(intent, REQUEST_CAMERA)
        }

        // 選擇 gallery
        mGallery.setOnClickListener {
            mDialog.dismiss()

            // open gallery
            val intent = Intent()
            intent.setType("image/jpg, image/png")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "From Gallery"), REQUEST_GALLERY)
        }
    }

    private fun initView() {
        mBtnUpload = this.findViewById(R.id.btn_upload)
        mPhotoResult = this.findViewById(R.id.upload_result)

        // 自製 menu: 要選擇 camera 還是 gallery
        mDialog = Dialog(this)
        mDialog.setContentView(R.layout.custom_menu_layout)
        mDialog.setTitle("Resource: ")

        mCamera = mDialog.findViewById(R.id.select_camera)
        mGallery = mDialog.findViewById(R.id.select_gallery)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            // 拍照的照片顯示在畫面上
            val photo = data?.getParcelableExtra<Bitmap>("data")
            mPhotoResult.setImageBitmap(photo)
        }
        else if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            // 選取的相簿照片顯示在畫面上
            val photo = data?.data
            mPhotoResult.setImageURI(photo)
        }
    }
}