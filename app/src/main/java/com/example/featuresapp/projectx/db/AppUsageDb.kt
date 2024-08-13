package com.example.featuresapp.projectx.db

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(entities = [UsageModel::class, AppsModel::class], version = 3, exportSchema = false)
abstract class AppUsageDb : RoomDatabase() {
   abstract fun getUsageDao(): UsageDao
   abstract fun getAppsDao(): AppsDao
}