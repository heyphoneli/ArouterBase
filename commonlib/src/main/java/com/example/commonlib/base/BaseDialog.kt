package com.example.commonlib.base

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.viewbinding.ViewBinding
import com.example.commonlib.R
import com.gyf.immersionbar.ktx.destroyImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import java.lang.reflect.ParameterizedType

abstract class BaseDialog<K : ViewBinding>(private val context: Activity) :
	Dialog(context, R.style.CustomDialog) {
	
	protected lateinit var viewBinding: K
	abstract fun initView()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		requestWindowFeature(Window.FEATURE_NO_TITLE)
		initViewBinding()
		setContentView(viewBinding.root)
		immersionBar(context)
		window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
		window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
		initView()
	}
	
	
	override fun dismiss() {
		context.destroyImmersionBar(this)
		super.dismiss()
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
}