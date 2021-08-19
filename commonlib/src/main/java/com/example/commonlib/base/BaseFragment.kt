package com.example.commonlib.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<T : ViewBinding> : Fragment() {
	private var _viewBinding: T? = null
	protected val viewBinding: T get() = _viewBinding!!
	abstract fun doOnViewCreated()
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		initViewBinding()
		return viewBinding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		doOnViewCreated()
	}
	
	@Suppress("UNCHECKED_CAST")
	private fun initViewBinding() {
		val genericSuperclass = javaClass.genericSuperclass
		if (genericSuperclass is ParameterizedType) {
			val classVb = genericSuperclass.actualTypeArguments[0] as Class<*>
			val method = classVb.getMethod("inflate", LayoutInflater::class.java)
			_viewBinding = method.invoke(null, layoutInflater) as T
		} else {
			throw IllegalAccessException("范型错误")
		}
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_viewBinding = null
	}
}