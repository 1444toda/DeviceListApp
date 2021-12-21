package com.example.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeviceInfoBoxEntity(

    @PrimaryKey(autoGenerate = true) val id : Int,//データベース内でかぶりがないものをプライマリーキーに autoGenerate=trueでidを自動生成
    var name : String,
    var maker : String,
    var os : String?,
    var displaySize: Float?,
    var dateModified : String,
    val dateAdded : String
)
