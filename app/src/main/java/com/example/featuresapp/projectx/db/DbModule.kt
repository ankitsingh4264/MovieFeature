package com.example.featuresapp.projectx.db

import android.content.Context
import androidx.room.Room
import com.example.featuresapp.projectx.db.dao.AppsDao
import com.example.featuresapp.projectx.db.dao.UsageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object  DbModule{

    @Provides
    fun  getAppUsageDatabase(
       @ApplicationContext context: Context
    ): AppUsageDb {
        return Room.databaseBuilder(
            context,
            AppUsageDb::class.java,
            "app_usage_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun getUsageDao(
        appUsageDb: AppUsageDb
    ): UsageDao {
        return appUsageDb.getUsageDao()
    }
    @Provides
    fun getAppsDao(
        appUsageDb: AppUsageDb
    ): AppsDao {
        return appUsageDb.getAppsDao()
    }
}