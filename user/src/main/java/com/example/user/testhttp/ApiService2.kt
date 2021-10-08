package com.example.user.testhttp

import com.example.user.testhttp.ApiModel2
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService2 {
	@GET("api-qq.name")
	suspend fun getQQ(@Query("qq") qq: String): ApiModel2
}