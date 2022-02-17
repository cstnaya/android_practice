package com.example.androidpractice.lesson2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (val name: String,
                 val age: Int): Parcelable {}

/*
*  原本應該要有一個類似下方的創建函式，把參數按照順序丟進來
*  User (Parcel in) {
*     name = in.readString()
*     age = in.readInt()
*  }
*
*  原本還得實作很多 abstract 函式，這邊安裝了 Parcelize 插件，系統會幫忙自動設置，就可以忽略了
*  如果想要系統自動化設置， 在 data class 前面放 @Parcelize 即可 (記得先安裝)
*
*  詳細可以讀讀看: http://hulkyang.blogspot.com/2019/10/kotlin-androidparcelable.html
*  安裝看此: https://stackoverflow.com/a/64925204
* */