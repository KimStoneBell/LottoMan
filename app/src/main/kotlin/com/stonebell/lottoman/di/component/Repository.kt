package com.stonebell.lottoman.di.component

import com.stonebell.lottoman.di.module.data.RepositoryModule
import com.stonebell.lottoman.domain.ILottoRepository
import com.stonebell.lottoman.domain.ILottoStoreRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface Repository{
    fun lottoDataRepo() : ILottoRepository
    fun lottoStoreRepo() : ILottoStoreRepository
}