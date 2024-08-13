package com.example.featuresapp.projectx.db

import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey

@Entity(tableName = "Apps")
data class AppsModel(
    @PrimaryKey
    val packageName: String,
    val appName:String?=null,
    val isSelected:Boolean = false
)