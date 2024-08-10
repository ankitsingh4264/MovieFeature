package com.example.featuresapp.projectx.utils

import java.util.Calendar

object AppUtils {
    const val instaPackageName = "com.instagram.android"
    const val launcherPackageName = "com.android.launcher"



    val supportedPackageName = setOf<String>(
        instaPackageName
    )

     val packageAppNameMapping = mutableMapOf(
        instaPackageName to "Instagram",
         )

    fun getTodayAtMidnight(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

}

fun String?.isPackageNameSupported(): Boolean {
    if (this == null) return false
    return AppUtils.supportedPackageName.contains(this)
}