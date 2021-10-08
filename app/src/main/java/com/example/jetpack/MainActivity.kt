package com.example.jetpack

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.example.commonlib.base.BaseActivity
import com.example.jetpack.databinding.ActivityMainBinding
import com.example.jetpack.testhttp.*
import com.example.network.Network

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
	override fun doOnCreate(savedInstanceState: Bundle?) {
//		viewModel.successLiveData.observe(this, {
//			println((it.data as ApiPoetryModel).jieshi)
//		})
//		viewModel.failLiveData.observe(this, {
//			println(it.msg)
//		})
//		viewModel.successStringLiveData.observe(this, {
//			println(it)
//		})
		viewBinding.navView.setOnItemSelectedListener {
			val path = when (it.itemId) {
				R.id.navigation_home -> {
//					request {
//						val poetry = Network.create(ApiService2::class.java, NetworkImp2()).getQQ("2394449009")
//						println()
//					}
					"/home/homeFragment"
				}
				R.id.navigation_dashboard -> {
//					viewModel.get1("草")
					"/cart/cartFragment"
				}
				else -> {
//					viewModel.get1("艹")
					"/mine/mineFragment"
				}
			}
			navigateToPath(path)
			return@setOnItemSelectedListener true
		}
		navigateToPath("/home/homeFragment")
		
	}
	
	private fun navigateToPath(path: String) {
		val fragment = ARouter.getInstance().build(path).navigation() as Fragment
		supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
	}
	
	override fun isImmersionEnable(): Boolean {
		return false
	}
	
}