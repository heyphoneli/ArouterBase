package com.example.jetpack

import androidx.lifecycle.viewModelScope
import com.example.commonlib.base.BaseViewModel
import com.example.jetpack.testhttp.ApiService
import com.example.jetpack.testhttp.NetworkImp
import com.example.network.Network
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
	fun get1(w: String) {
		doHttpRequest {
			Network.create(ApiService::class.java, NetworkImp()).chengyu(w)
		}
	}
	
	
	fun get2() {
		viewModelScope.launch {
			val baidu = Network.create(ApiService::class.java, NetworkImp()).getBaidu("http://www.baidu.com")
			successStringLiveData.postValue(baidu)
		}
	}
}