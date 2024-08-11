package com.example.featuresapp.projectx.repo

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun getUserRepo(usageRepoImpl: AppUsageRepoImpl):AppUsageRepo
}