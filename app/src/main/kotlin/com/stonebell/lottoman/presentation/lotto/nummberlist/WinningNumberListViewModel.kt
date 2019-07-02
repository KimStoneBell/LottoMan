package com.stonebell.lottoman.presentation.lotto.nummberlist

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.stonebell.lottoman.data.repository.LottoDataRepository
import com.stonebell.lottoman.di.component.DaggerRepository
import com.stonebell.lottoman.domain.ILottoRepository


class WinningNumberListViewModel : ViewModel(){

    private val database: ILottoRepository by lazy { DaggerRepository.create().lottoDataRepo() }

    val lottoWinningDatas by lazy {

        val process = database.getAllLottoData().toFlowable()

        LiveDataReactiveStreams.fromPublisher(process)
    }
}