package com.example.featuresapp.projectx.repo

import android.util.Log
import com.example.featuresapp.projectx.db.AppsDao
import com.example.featuresapp.projectx.db.AppsModel
import com.example.featuresapp.projectx.db.UsageDao
import com.example.featuresapp.projectx.db.UsageModel
import com.example.featuresapp.projectx.utils.AppUtils
import com.example.featuresapp.projectx.utils.AppUtils.getTodayAtMidnight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppUsageRepoImpl @Inject constructor(
     val usageDao: UsageDao,
     val appsDao: AppsDao
) : AppUsageRepo{
    //to prevent race updation of data
    private val mutex = Mutex(locked = false)

    override suspend fun addUsageData(packageName:String,duration: Long) {
        withContext(Dispatchers.IO){
            mutex.lock()
            usageDao.getUsageDataForDate(packageName,getTodayAtMidnight()).let {
                if (it != null){
                    usageDao.updateData(UsageModel(packageName, it.appName, date = getTodayAtMidnight(), duration = it.duration + duration))
                }else{
                    val appName = AppUtils.packageAppNameMapping[packageName]
                    usageDao.insertData(UsageModel(packageName,appName, date = getTodayAtMidnight(), duration = duration))
                }
            }
            mutex.unlock()

        }
    }

    override suspend fun getAppUsageDataForPackage(packageName: String, date: Long): UsageModel? {
        return withContext(Dispatchers.Default){
           val data =  usageDao.getUsageDataForDate(packageName,date)
            data

        }
    }



    override suspend fun getTotalVideoUsageDate(date:Long):Long {
       return withContext(Dispatchers.Default){
            val appUsages =   usageDao.getAllUsageForDate(getTodayAtMidnight())
           var totalTime =0L
              appUsages?.forEach {
                totalTime += it.duration
              }

           totalTime
       }
    }

    override suspend fun addApps(appsModel: AppsModel) {
        withContext(Dispatchers.IO){
            appsDao.insertData(appsModel)
        }

    }

}