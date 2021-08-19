package com.example.commonlib.service

import com.alibaba.android.arouter.facade.template.IProvider

interface LoginStatusService : IProvider {
	//判断是否登录，返回登录时间
	fun isLogin(): Long
}