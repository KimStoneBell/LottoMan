package com.stonebell.lottoman.presentation.lotto.make

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.stonebell.lottoman.R
import com.stonebell.lottoman.databinding.ActivityLottoDbMakerBinding

/**
 * 파이어베이스에 DB쌓을 용도로 일단 만들어봄.
 * DB에 저장할 회차를 입력받음 (시작회차, 끝 회차)
 * 받은내역 Firebase DB에 저장
 */

class LottoDBMakeActivity : AppCompatActivity() {
    private val dbViewModel: LottoDBMakeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityLottoDbMakerBinding>(this, R.layout.activity_lotto_db_maker).apply {
            viewModel = dbViewModel
            lifecycleOwner = this@LottoDBMakeActivity
        }
    }
}