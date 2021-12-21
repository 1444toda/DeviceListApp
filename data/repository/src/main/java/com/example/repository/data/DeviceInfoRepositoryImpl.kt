package com.example.repository.data

import com.example.db.AppDatabase
import com.example.db.dao.DeviceInfoDao
import com.example.db.entity.DeviceInfoBoxEntity
import com.example.model.DeviceInfoBox
import com.example.repository.DeviceInfoRepository
import com.example.repository.toEntity
import com.example.repository.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DeviceInfoRepositoryImpl @Inject constructor(
    private val dao: DeviceInfoDao
) : DeviceInfoRepository{
    override fun getAll(): Flow<List<DeviceInfoBox>> {
        return dao.getAll().map { list ->
            list.toModel()
        }
    }

    override fun get(id: Int): Flow<DeviceInfoBox?> {
        return dao.get(id).map {
            it?.toModel()
        }
    }

    override fun searchDeviceInfoByName(str: String): Flow<List<DeviceInfoBox>>{
        return dao.searchDeviceInfoByName(str).map { list ->
            list.toModel()
        }
    }

    override fun searchDeviceInfoByMaker(str: String): Flow<List<DeviceInfoBox>> {
        return dao.searchDeviceInfoByMaker(str).map { list->
            list.toModel()
        }
    }

    override suspend fun update(deviceInfoBox: DeviceInfoBox) {
        withContext(Dispatchers.IO) {
            dao.update(deviceInfoBox.toEntity())
        }
    }

    override suspend fun add(deviceInfoBox: DeviceInfoBox) {
        //スレッドの切り替え IO Input Output
        withContext(Dispatchers.IO) {
            dao.insert(deviceInfoBox.toEntity())
        }
    }

    override suspend fun remove(deviceInfoBox: DeviceInfoBox) {
        withContext(Dispatchers.IO) {
            dao.delete(deviceInfoBox.toEntity())
        }
    }
}