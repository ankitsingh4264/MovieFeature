package com.example.featuresapp.projectx.db.entity

import androidx.room.Entity


@Entity(tableName = "UsageModel", primaryKeys = ["packageName","date"])
data class UsageModel(
    val packageName: String,
    val appName:String?=null,
    val duration:Long,
    val date:Long
)
