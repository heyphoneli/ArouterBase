package com.example.user.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.example.commonlib.service.LoginStatusService
import com.example.network.Network
import com.example.user.UserConstants
import com.example.user.testhttp.ApiService
import com.example.user.testhttp.NetworkImp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Route(path = "/user/loginService", name = "loginService")
class LoginStatusServiceImp : LoginStatusService {
	override fun isLogin(): Long {
		val lastLogin = SPUtils.getInstance().getLong(UserConstants.SP_LOGIN_TIME)
		return if (System.currentTimeMillis() - lastLogin > UserConstants.LOGIN_EXPIRE) 0 else lastLogin
	}
	
	override fun init(context: Context?) {
		LogUtils.e("LoginStatusServiceImp init ...")
	}
}