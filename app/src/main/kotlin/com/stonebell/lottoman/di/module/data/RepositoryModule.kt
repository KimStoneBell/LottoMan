package com.stonebell.lottoman.di.module.data

import com.stonebell.lottoman.data.repository.LottoDataRepository
import com.stonebell.lottoman.data.repository.LottoStoreRepository
import com.stonebell.lottoman.domain.ILottoRepository
import com.stonebell.lottoman.domain.ILottoStoreRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [DataSourceModule::class])
abstract class RepositoryModule{
    @Binds
    @Singleton
    abstract fun provideLottoRepository(repository : LottoDataRepository) : ILottoRepository

    @Binds
    @Singleton
    abstract fun provideStoreRepository(repository : LottoStoreRepository) : ILottoStoreRepository
}