package com.example.featuresapp.projectx.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.featuresapp.projectx.db.entity.AppsModel

@Dao
interface AppsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(appsModel: AppsModel)

    @Update
    suspend fun updateAppSelection(appsModel: AppsModel)

    @Query("SELECT * FROM Apps")
    suspend fun getAllApps(): List<AppsModel>

    @Query("SELECT * FROM Apps WHERE isBlocked = 1")
    suspend fun getAllBlockedApps():List<AppsModel>


}