package com.example.user.testhttp

import com.example.network.INetworkConfigure
import okhttp3.OkHttpClient

class NetworkImp2 : INetworkConfigure {
	override fun isDebugEnv(): Boolean {
		return true
	}
	
	override fun baseUrl(): String {
		return "http://res.abeim.cn/"
	}
	
	
}