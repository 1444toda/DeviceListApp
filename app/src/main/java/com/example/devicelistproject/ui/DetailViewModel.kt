package com.example.devicelistproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.model.DeviceInfoBox
import com.example.repository.DeviceInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DeviceInfoRepository
) : ViewModel() {

    fun getDeviceInfo(id: Int): LiveData<DeviceInfoBox?> = repository.get(id).asLiveData()
}