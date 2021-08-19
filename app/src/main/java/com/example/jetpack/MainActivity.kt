package com.example.jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.example.commonlib.base.BaseActivity
import com.example.jetpack.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
	
	override fun doOnCreate(savedInstanceState: Bundle?) {
		viewBinding.navView.setOnItemSelectedListener {
			val path = when (it.itemId) {
				R.id.navigation_home ->
					"/home/homeFragment"
				R.id.navigation_dashboard ->
					"/cart/cartFragment"
				else ->
					"/mine/mineFragment"
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