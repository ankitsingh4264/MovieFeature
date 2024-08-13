package com.example.featuresapp.projectx.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(appsModel: AppsModel)

    @Query("UPDATE Apps SET isSelected = :isSelected WHERE packageName = :packageName")
    suspend fun updateAppSelection(packageName: String, isSelected: Boolean)

    @Query("SELECT * FROM Apps")
    suspend fun getAllApps(): List<AppsModel>

}