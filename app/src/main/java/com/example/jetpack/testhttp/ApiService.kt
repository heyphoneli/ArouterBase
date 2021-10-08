package com.example.jetpack.testhttp

import com.example.network.BaseApiModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
	@GET("api/Gushici")
	suspend fun getPoetry(): BaseApiModel<ApiPoetryModel>
	
	@GET("api/chengyujielong")
	suspend fun chengyu(@Query("word") word: String): BaseApiModel<ApiPoetryModel>
	
	@GET
	suspend fun getBaidu(@Url url: String): String
}