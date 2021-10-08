package com.example.commonlib.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.BaseApiModel
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
	val successLiveData = MutableLiveData<BaseApiModel<*>>()
	val failLiveData = MutableLiveData<BaseApiModel<*>>()
	val loadingLiveData = MutableLiveData<Boolean>()
	
	val successStringLiveData = MutableLiveData<String>()
	
	protected inline fun doHttpRequest(noinline block: suspend () -> BaseApiModel<*>) {
		viewModelScope.launch {
			loadingLiveData.postValue(true)
			kotlin.runCatching {
				val resp = block()
				if (resp.code == 200) {
					successLiveData.postValue(resp)
					return@runCatching
				}
				failLiveData.postValue(resp)
			}.onFailure {
				failLiveData.postValue(BaseApiModel.netErr())
			}
		}
	}
}

