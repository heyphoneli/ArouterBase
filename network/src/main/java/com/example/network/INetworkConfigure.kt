package com.example.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * 配置网络库
 */
interface INetworkConfigure {
	/**
	 * 是否debug环境
	 */
	fun isDebugEnv(): Boolean
	
	/**
	 * API base url，必须以"/"结尾
	 */
	fun baseUrl(): String
	
	/**
	 * okHttp 相关配置
	 */
	val okHttpConfig: OkHttpConfig
		get() = object : OkHttpConfig {
			override fun customOkHttpClient(): OkHttpClient? {
				return null
			}
		}
	
	/**
	 * 配置 okHttp
	 */
	interface OkHttpConfig {
		/**
		 * 超时时间(毫秒）
		 */
		val timeout: Long get() = 60 * 1000L
		
		/**
		 * 日志信息
		 */
		val logInfo: LogInfo get() = object : LogInfo {}
		
		/**
		 * 拦截器
		 */
		val interceptors: List<Interceptor> get() = mutableListOf()
		
		/**
		 * 显示详细的http请求过程log
		 */
		val showHttpDetailLog: Boolean get() = false
		
		/**
		 * 无法满足以上配置，可以自定义okhttpclient
		 */
		fun customOkHttpClient(): OkHttpClient?
	}
	
	/**
	 * 配置日志
	 */
	interface LogInfo {
		val tagName: String get() = "http-log"
		val level: Int get() = Log.INFO
	}
}