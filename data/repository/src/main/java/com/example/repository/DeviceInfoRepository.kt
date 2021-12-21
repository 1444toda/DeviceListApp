package com.example.repository

import com.example.db.entity.DeviceInfoBoxEntity
import com.example.model.DeviceInfoBox
import kotlinx.coroutines.flow.Flow

interface DeviceInfoRepository {
    fun getAll(): Flow<List<DeviceInfoBox>>

    fun get (id: Int) : Flow<DeviceInfoBox?>

    fun searchDeviceInfoByName(str: String) : Flow<List<DeviceInfoBox>>

    fun searchDeviceInfoByMaker(str: String) : Flow<List<DeviceInfoBox>>

    suspend fun update(deviceInfoBox: DeviceInfoBox)

    suspend fun add(deviceInfoBox: DeviceInfoBox)

    suspend fun remove(deviceInfoBox: DeviceInfoBox)
}