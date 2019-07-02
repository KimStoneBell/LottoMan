package com.stonebell.lottoman.di.module.data

import com.stonebell.lottoman.data.datasource.FirebaseLottoDataSource
import com.stonebell.lottoman.data.datasource.FirebaseLottoStoreDataSource
import com.stonebell.lottoman.data.repository.ILottoDataSource
import com.stonebell.lottoman.data.repository.ILottoStoreSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule{
    @Provides
    @Singleton
    fun provideLottoDataSource() : ILottoDataSource = FirebaseLottoDataSource()

    @Provides
    @Singleton
    fun provideStoreDataSource() : ILottoStoreSource = FirebaseLottoStoreDataSource()
}