package com.stonebell.lottoman.presentation

import android.content.Context
import android.content.Intent
import com.stonebell.lottoman.presentation.lotto.scanner.QrcodeScannerActivity
import com.stonebell.lottoman.presentation.lotto.make.LottoDBMakeActivity
import com.stonebell.lottoman.presentation.lotto.nummberlist.WinningNumberListActivity
import com.stonebell.lottoman.presentation.lotto.search.LottoSearchDBActivity
import com.stonebell.lottoman.presentation.lotto.store.StoreMapActivity

class NavigationController(val context: Context){

    fun startLottoDBMakeActivity(){
        context.startActivity(
                Intent(context, LottoDBMakeActivity::class.java)
                        .apply { flags += Intent.FLAG_ACTIVITY_NEW_TASK }
        )
    }

    fun startLottoSearchDBActivity(){
        context.startActivity(
                Intent(context, LottoSearchDBActivity::class.java)
                        .apply { flags += Intent.FLAG_ACTIVITY_NEW_TASK })
    }

    fun startQrcodeScannerActivity(){
        context.startActivity(
                Intent(context, QrcodeScannerActivity::class.java)
                        .apply { flags += Intent.FLAG_ACTIVITY_NEW_TASK })
    }

    fun startStoreMapActivity(){
        context.startActivity(
                Intent(context, StoreMapActivity::class.java)
                        .apply { flags += Intent.FLAG_ACTIVITY_NEW_TASK })
    }

    fun startWinningListActivity(){
        context.startActivity(
                Intent(context, WinningNumberListActivity::class.java)
                        .apply { flags += Intent.FLAG_ACTIVITY_NEW_TASK })
    }
}