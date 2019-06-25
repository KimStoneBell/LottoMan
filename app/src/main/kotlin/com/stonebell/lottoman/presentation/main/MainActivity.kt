package com.stonebell.lottoman.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.stonebell.lottoman.R
import com.stonebell.lottoman.databinding.ActivityMainBinding
import com.stonebell.lottoman.presentation.NavigationController

/**
 * 1. 최초 FirebaseDB에서 마지막 회차에 대한 데이터를 가져옴
 * 2. 마지막회차에 대한 데이터로드 후 화면에 보여줌.?
 * 3. 전체 회차에 대한 데이터는 로컬에 한번에 다 받아서 보관? 필요할 때 받아서 보관?
 * 4.
 */

class MainActivity : AppCompatActivity() {

    private val navigationController : NavigationController by lazy { NavigationController(baseContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            navigator = navigationController
        }
    }
}

