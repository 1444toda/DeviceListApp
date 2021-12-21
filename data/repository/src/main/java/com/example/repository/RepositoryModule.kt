package com.example.repository

import com.example.repository.data.DeviceInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindDeviceInfoRepository(
        impl:DeviceInfoRepositoryImpl
    ):DeviceInfoRepository
}