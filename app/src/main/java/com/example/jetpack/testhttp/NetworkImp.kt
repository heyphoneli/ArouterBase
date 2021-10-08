package com.example.jetpack.testhttp

import android.util.Log
import com.example.network.INetworkConfigure
import okhttp3.OkHttpClient

class NetworkImp : INetworkConfigure {
	override fun isDebugEnv(): Boolean {
		return true
	}
	
	override fun baseUrl(): String {
		return "http://api.muxiaoguo.cn/"
	}
	
	override val okHttpConfig: INetworkConfigure.OkHttpConfig
		get() = object : INetworkConfigure.OkHttpConfig {
			override fun customOkHttpClient(): OkHttpClient? {
				return null
			}
			
			override val logInfo: INetworkConfigure.LogInfo
				get() = object : INetworkConfigure.LogInfo {
					override val tagName: String = "super.tagName"
					override val level: Int = Log.ERROR
				}
		}
	
}