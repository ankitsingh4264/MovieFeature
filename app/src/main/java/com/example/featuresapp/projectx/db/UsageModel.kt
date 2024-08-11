package com.example.featuresapp.projectx.db

import android.content.ComponentName
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "UsageModel", primaryKeys = ["packageName","date"])
data class UsageModel(
    val packageName: String,
    val appName:String?=null,
    val duration:Long,
    val date:Long
)
