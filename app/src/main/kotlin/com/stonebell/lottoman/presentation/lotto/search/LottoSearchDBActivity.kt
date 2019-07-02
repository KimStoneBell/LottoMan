package com.stonebell.lottoman.presentation.lotto.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.stonebell.lottoman.R
import com.stonebell.lottoman.databinding.ActivityLottoSearchDbBinding


class LottoSearchDBActivity: AppCompatActivity(){

    private val searchViewModel: LottoSearchDBViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityLottoSearchDbBinding>(this, R.layout.activity_lotto_search_db).apply {
            viewModel = searchViewModel
            lifecycleOwner = this@LottoSearchDBActivity
        }
    }
}