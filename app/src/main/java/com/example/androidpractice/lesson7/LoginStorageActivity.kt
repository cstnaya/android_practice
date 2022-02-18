package com.example.androidpractice.lesson7

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.androidpractice.R
import java.io.*

/*
*  為了確認這次的結果，需要先去官網下載 platform-tools
*  使用此工具的方式: 解壓縮後在其資料夾內開啟 cmd 即可
*  1. adb devices 確認有裝置正在運行
*  2. adb shell 開始使用指令
*  3. pwd 顯示目前位置 (應該是 "/")
*  4. 新版有權限限制，所以要輸入 run-as [your-package-name] (例如我是: com.example.androidpractice)
*  5. ls 會看到 info.text 就是成功
* */

class LoginStorageActivity: Activity() {
    lateinit var mAccount: EditText
    lateinit var mPassword: EditText
    lateinit var mLoginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_storage)

        initView()
        initListener()
    }

    override fun onResume() {
        super.onResume()
        // 在畫面可見的時候 (可能是你結束程式又再打開，或是程式跑到後台又回到前台) ，要準備之前輸入過的帳密

        try {
            // 1. 打開檔案
            val fileInputStream: FileInputStream = this.openFileInput("info.txt")

            // 2. 讀取檔案內容
            val reader: BufferedReader = BufferedReader(InputStreamReader(fileInputStream))
            val info: String = reader.readLine()
            val splits = info.split(',').toTypedArray()

            // 3. 設置到畫面上
            val account = splits[0]
            val password = splits[1]
            mAccount.setText(account)
            mPassword.setText(password)
        } catch (e: Exception) {}
    }

    private fun initListener() {
        mLoginBtn.setOnClickListener {
            handleLoginEvent()
        }
    }

    private fun handleLoginEvent() {
        val accountText: String = mAccount.text.toString().trim()
        val passwordText: String = mPassword.text.toString().trim()

        if (TextUtils.isEmpty(accountText) || TextUtils.isEmpty(passwordText)) {
            Toast.makeText(this, "您有項目未輸入!", Toast.LENGTH_SHORT).show()
            return
        }

        // 剛剛輸入的東西設為空
        mAccount.setText("")
        mPassword.setText("")

        // 剛剛存的東西拿去儲存
        saveUserInfo(accountText, passwordText)
    }

    private fun saveUserInfo(accountText: String, passwordText: String) {
        // 安全的方法獲得 /data/data/[package-name]/files => this.firesDir
        // 想要存在這個目錄下的 info.txt 裡面 => 要添加 child: info.txt
        // 全路徑: "/data/data/com.example.androidpractice/files/info.txt"

        try {
            // 1. 安全地獲取檔案位置
            val fileDir: File = this.filesDir
            val file: File = File(fileDir, "info.txt")
            Log.d("LoginStorageActivity", "Files path: " + fileDir.toString())

            // 如果是想存在 cache 裡面
            val cache: File = this.cacheDir
            Log.d("LoginStorageActivity", "Cache path: " + cache.toString())

            /*
            * Log 結果:
            * Files path: /data/user/0/com.example.androidpractice/files => 我們用來保存文件用的。可以手動刪除 (在系統那邊刪除)，也可以寫程式碼刪除
            * Cache path: /data/user/0/com.example.androidpractice/cache => 系統設置的緩存路徑，只存 cache 用。此目錄內的文建會由系統根據存儲情形自行清理
            * */

            // 2. 若檔案不存在就先建立新檔案
            if (!file.exists()) {
                file.createNewFile()
            }

            // 3. 寫入文件內容
            val fos = FileOutputStream(file)
            fos.write( "$accountText,$passwordText".toByteArray()  )

            // 4. 關閉文件
            fos.close()

            Toast.makeText(this, "資料儲存成功!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {}
    }

    private fun initView() {
        mAccount = this.findViewById(R.id.account)
        mPassword = this.findViewById(R.id.password)
        mLoginBtn = this.findViewById(R.id.login_btn)
    }
}