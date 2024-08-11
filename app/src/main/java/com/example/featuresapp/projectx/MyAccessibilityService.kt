package com.example.featuresapp.projectx

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.media.AudioManager
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.example.featuresapp.projectx.db.AppUsageDb
import com.example.featuresapp.projectx.db.UsageDao
import com.example.featuresapp.projectx.repo.AppUsageRepo
import com.example.featuresapp.projectx.utils.AppUtils
import com.example.featuresapp.projectx.utils.isPackageNameSupported
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MyAccessibilityService : AccessibilityService() {

    @Inject
    lateinit var repo:AppUsageRepo

    private var currentRunningPackageName:String = ""
    private var videoStartTime:Long = 0L
    private val serviceScope = CoroutineScope(Dispatchers.Unconfined)


    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val className = event?.className?.toString()
        val packageName = event?.packageName?.toString()
        val audio =  this.getSystemService(AUDIO_SERVICE) as AudioManager
        if (packageName.isPackageNameSupported() && audio.isMusicActive) {
            currentRunningPackageName = event?.packageName.toString()
            if (videoStartTime == 0L) {
                videoStartTime = System.currentTimeMillis()
            }
        }else{
            if(currentRunningPackageName.isPackageNameSupported()){
                val duration = System.currentTimeMillis() - videoStartTime
                if (duration > 0){
                    serviceScope.launch {
                        repo.addUsageData(currentRunningPackageName,duration)
                    }
                }
                resetPackageDetails()
            }

        }

    }
    private fun resetPackageDetails(){
        currentRunningPackageName = ""
        videoStartTime = 0L
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

}