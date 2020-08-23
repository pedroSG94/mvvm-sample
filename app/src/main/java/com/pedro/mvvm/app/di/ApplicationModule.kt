package com.pedro.mvvm.app.di

import android.content.Context
import androidx.room.Room
import com.pedro.mvvm.data.api.ApiRestImp
import com.pedro.mvvm.data.db.MyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    @Named("apikey")
    fun getApikey(): String = "ahsidgwiob w qpddq14342 2"

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): MyDataBase {
        return Room.databaseBuilder(context, MyDataBase::class.java, "myDB").build()
    }

    @Provides
    @Singleton
    fun provideApiRest(): ApiRestImp = ApiRestImp()
}