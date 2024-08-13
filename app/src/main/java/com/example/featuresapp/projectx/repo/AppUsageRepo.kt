package com.example.featuresapp.projectx.repo

import com.example.featuresapp.projectx.db.entity.AppsModel
import com.example.featuresapp.projectx.db.entity.UsageModel
import com.example.featuresapp.projectx.utils.AppUtils

interface AppUsageRepo {
     suspend fun addUsageData(packageName:String,duration: Long)
     suspend fun getAppUsageDataForPackage(packageName: String,date: Long = AppUtils.getTodayAtMidnight()): UsageModel?
     suspend fun getTotalVideoUsageDate(date:Long):Long
     suspend fun addApps(appsModel: AppsModel)
}