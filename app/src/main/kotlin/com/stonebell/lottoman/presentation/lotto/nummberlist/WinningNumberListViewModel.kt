package com.stonebell.lottoman.presentation.lotto.nummberlist

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.stonebell.lottoman.data.repository.LottoDataRepository
import com.stonebell.lottoman.domain.ILottoRepository
import io.reactivex.processors.PublishProcessor



class WinningNumberListViewModel : ViewModel(){

    private val database: ILottoRepository by lazy { LottoDataRepository() }
    private val processor = PublishProcessor.create<String>()

    val lottoWinningDatas = LiveDataReactiveStreams.fromPublisher(database.getAllLottoData().toFlowable())

    fun connectionData(){

    }
}