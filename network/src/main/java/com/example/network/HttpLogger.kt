package com.example.network

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

/**
 * http请求日志类
 */
class HttpLogger(private var logInfo: INetworkConfigure.LogInfo) : HttpLoggingInterceptor.Logger {
	
	override fun log(message: String) {
		Log.println(logInfo.level, logInfo.tagName, message)
	}
}