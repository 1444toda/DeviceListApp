package com.example.db.dao

import androidx.room.*
import com.example.db.entity.DeviceInfoBoxEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceInfoDao {
    @Query("SELECT * FROM DeviceInfoBoxEntity")
    fun getAll(): Flow<List<DeviceInfoBoxEntity>>

    @Query("SELECT * FROM DeviceInfoBoxEntity WHERE id = :id")
    fun get(id:Int): Flow<DeviceInfoBoxEntity?>

    @Query("SELECT * FROM DeviceInfoBoxEntity WHERE name LIKE '%' || :deviceName || '%'")
    fun searchDeviceInfoByName(deviceName:String): Flow<List<DeviceInfoBoxEntity>>

    @Query("SELECT * FROM DeviceInfoBoxEntity WHERE maker LIKE '%' || :deviceMaker || '%'")
    fun searchDeviceInfoByMaker(deviceMaker:String): Flow<List<DeviceInfoBoxEntity>>

    @Update
    suspend fun update(deviceInfoBoxEntity: DeviceInfoBoxEntity)

    @Insert
    suspend fun insert(deviceInfoBoxEntity: DeviceInfoBoxEntity)

    @Delete
    suspend fun delete(deviceInfoBoxEntity: DeviceInfoBoxEntity)
}