package com.example.commonlib.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding

interface IBaseView<VB : ViewBinding, VM : BaseViewModel> {
	fun doOnCreate(savedInstanceState: Bundle?)
	fun isImmersionEnable(): Boolean
	fun isStatusBarDarkFont(): Boolean
//	fun initViewBinding(): VB
//	fun initViewModel(): VM
	
}