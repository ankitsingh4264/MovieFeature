package com.example.featuresapp.projectx.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar

object AppUtils {
    const val instaPackageName = "com.instagram.android"
    const val launcherPackageName = "com.android.launcher"
    private val RESTRICTED_TIME = longPreferencesKey("restricted_time")

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


    suspend fun saveToDataStore(context: Context, dailyCap: Long) {
        context.dataStore.edit { preferences ->
            preferences[RESTRICTED_TIME] = dailyCap
        }
    }

    fun getRestrictedTime(context: Context): Flow<Long> {
        return context.dataStore.data
            .map { preferences ->
                preferences[RESTRICTED_TIME] ?: 0L
            }
    }


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