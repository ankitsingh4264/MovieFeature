package com.example.featuresapp.projectx.db

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(entities = [UsageModel::class], version = 2, exportSchema = false)
abstract class AppUsageDb : RoomDatabase() {
   abstract fun getUsageDao(): UsageDao
}