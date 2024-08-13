package com.example.featuresapp.projectx.viewmodel

import androidx.lifecycle.ViewModel
import com.example.featuresapp.projectx.db.entity.AppsModel
import com.example.featuresapp.projectx.repo.AppUsageRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(
    private val usageRepo: AppUsageRepo
):ViewModel() {

    suspend fun addData(appsModel: AppsModel) {
        usageRepo.addApps(appsModel)
    }

}