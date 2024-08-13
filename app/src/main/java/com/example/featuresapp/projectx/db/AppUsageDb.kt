package com.example.featuresapp.projectx.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.featuresapp.projectx.db.dao.AppsDao
import com.example.featuresapp.projectx.db.dao.UsageDao
import com.example.featuresapp.projectx.db.entity.AppsModel
import com.example.featuresapp.projectx.db.entity.UsageModel


@Database(entities = [UsageModel::class, AppsModel::class], version = 3, exportSchema = false)
abstract class AppUsageDb : RoomDatabase() {
   abstract fun getUsageDao(): UsageDao
   abstract fun getAppsDao(): AppsDao
}