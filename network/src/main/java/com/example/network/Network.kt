package com.example.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.LoggingEventListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * retrofit封装类
 */
object Network {
	//网络配置
	private var nullableNetworkConf: INetworkConfigure? = null
	private val iNetworkConf: INetworkConfigure get() = nullableNetworkConf!!
	
	//缓存retrofit对象
	private val retrofitMap = HashMap<String, Retrofit>()
	
	/**
	 * 默认okhttp配置
	 */
	private fun defaultOkHttpClient(): OkHttpClient {
		val config = iNetworkConf.okHttpConfig
		val builder = OkHttpClient.Builder()
		if (iNetworkConf.isDebugEnv()) {
			//debug模式下，打印日志
			val logger = HttpLogger(config.logInfo)
			builder.addInterceptor(HttpLoggingInterceptor(logger).also {
				it.level = HttpLoggingInterceptor.Level.BODY
			})
			//打印请求过程详细日志
			if (config.showHttpDetailLog)
				builder.eventListenerFactory(LoggingEventListener.Factory(logger))
		}
		//配置拦截器
		config.interceptors.forEach {
			builder.addInterceptor(it)
		}
		return builder.connectTimeout(config.timeout, TimeUnit.MILLISECONDS)
			.readTimeout(config.timeout, TimeUnit.MILLISECONDS)
			.writeTimeout(config.timeout, TimeUnit.MILLISECONDS)
			.build()
	}
	
	/**
	 * 获取retrofit
	 * 若缓存中不存在，则创建新的对象并添加到缓存中
	 */
	private fun retrofit(clazz: Class<*>): Retrofit {
		return retrofitMap[iNetworkConf.baseUrl() + clazz.name]
			?: Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(iNetworkConf.baseUrl())
				.client(iNetworkConf.okHttpConfig.customOkHttpClient() ?: defaultOkHttpClient())
				.build().also { retrofitMap[iNetworkConf.baseUrl() + clazz.name] = it }
	}
	
	/**
	 * 获取服务接口
	 * 请求方式：Network.create(Service::class.java,INetworkConfigure).method
	 */
	fun <T> create(clazz: Class<T>, configure: INetworkConfigure): T {
		nullableNetworkConf = configure
		return retrofit(clazz).create(clazz)
	}
	
}