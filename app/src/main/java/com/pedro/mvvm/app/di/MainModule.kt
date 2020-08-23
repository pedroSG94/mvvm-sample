package com.pedro.mvvm.app.di

import com.pedro.mvvm.data.api.ApiRestImp
import com.pedro.mvvm.data.db.MyDataBase
import com.pedro.mvvm.task.ApiRestRepository
import com.pedro.mvvm.task.DataBaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class MainModule {

    @Provides
    fun provideDataBaseRepository(db: MyDataBase) = DataBaseRepository(db)

    @Provides
    fun provideApiRestRepository(apiRest: ApiRestImp) = ApiRestRepository(apiRest)
}