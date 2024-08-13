package com.example.featuresapp.projectx.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Apps")
data class AppsModel(
    @PrimaryKey
    val packageName: String,
    val appName:String?=null,
    val isBlocked:Boolean = false
)