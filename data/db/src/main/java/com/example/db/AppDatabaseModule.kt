package com.example.db

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.db.dao.DeviceInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.security.AccessControlContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Singleton //シングルトン設計
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL( "DROP TABLE DeviceInfoBoxEntity")
                database.execSQL(
                    "CREATE TABLE DeviceInfoBoxEntity (" +
                            " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                            " name TEXT NOT NULL , " +
                            " maker TEXT NOT NULL , " +
                            " os TEXT , " +
                            " displaySize REAL , " +
                            " dateModified TEXT NOT NULL , " +
                            " dateAdded TEXT NOT NULL " +
                            ");")

                //バージョン1→2にあげる処理
            }
        }
        return Room.databaseBuilder(context, AppDatabase::class.java,"app_database").addMigrations(MIGRATION_1_2).build()
    }

    @Provides
    fun provideDeviceInfoDao(appDatabase: AppDatabase) : DeviceInfoDao = appDatabase.deviceInfoDao()

}