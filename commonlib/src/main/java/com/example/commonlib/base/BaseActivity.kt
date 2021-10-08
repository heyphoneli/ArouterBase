package com.example.commonlib.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.example.commonlib.service.LoginStatusService
import com.gyf.immersionbar.ktx.immersionBar
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<K : ViewBinding, T : BaseViewModel> : AppCompatActivity(), IBaseView<K,T> {
	
	protected lateinit var viewBinding: K
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initViewBinding()
		setContentView(viewBinding.root)
		if (isImmersionEnable()) {
			immersionBar {
				statusBarDarkFont(isStatusBarDarkFont())
			}
		}
		doOnCreate(savedInstanceState)
	}
	
	
	@Suppress("UNCHECKED_CAST")
	private fun initViewBinding() {
		val genericSuperclass = javaClass.genericSuperclass
		if (genericSuperclass is ParameterizedType) {
			val classVb = genericSuperclass.actualTypeArguments[0] as Class<*>
			val method = classVb.getMethod("inflate", LayoutInflater::class.java)
			viewBinding = method.invoke(null, layoutInflater) as K
		} else {
			throw IllegalAccessException("范型错误")
		}
	}
	
	/**
	 * 是否启用immersion
	 */
	override fun isImmersionEnable(): Boolean {
		return true
	}
	
	/**
	 * 状态栏字体是否是黑色
	 */
	override fun isStatusBarDarkFont(): Boolean {
		return true
	}
	
	
}