package com.example.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.db.dao.DeviceInfoDao
import com.example.db.entity.DeviceInfoBoxEntity

@Database(
    entities = [DeviceInfoBoxEntity::class],
    version = 2
)
abstract class AppDatabase :RoomDatabase(){
    abstract fun deviceInfoDao():DeviceInfoDao
}