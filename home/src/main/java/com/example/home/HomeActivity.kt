package com.example.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlib.base.BaseActivity
import com.example.home.databinding.ActivityHomeBinding

/**
 * 单独运行时才需要，否则只提供homefragment
 */
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
	override fun doOnCreate(savedInstanceState: Bundle?) {
	}
}