package com.example.featuresapp.projectx.db

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface UsageDao {
   @Query("SELECT * FROM UsageModel WHERE packageName = :packageName")
   suspend  fun getPackageData(packageName:String):UsageModel?

  @Insert
  suspend fun insertData(usageModel: UsageModel)
    @Update
    suspend fun updateData(usageModel: UsageModel)

    @Query("SELECT * FROM UsageModel WHERE packageName = :packageName AND date = :date limit 1")
    suspend fun getUsageDataForDate(packageName: String,date:Long):UsageModel?

    @Query("SELECT * FROM UsageModel WHERE date = :date")
    suspend fun getAllUsageForDate(date:Long):List<UsageModel>?
}