package com.example.jetpack

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.example.commonlib.service.LoginStatusService
import com.example.commonlib.utils.Constants

@Interceptor(priority = 1, name = "LoginInterceptor")
class LoginInterceptor : IInterceptor {
	@JvmField
	@Autowired(name = "/user/loginService")
	var loginStatusService: LoginStatusService? = null
	
	override fun init(context: Context?) {
		//加入注入才能使用autowired
		ARouter.getInstance().inject(this)
	}
	
	override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
		LogUtils.e("进入拦截器了")
		if (postcard?.extra == Constants.ROUTER_EXTRA_NEED_LOGIN) {
			//页面标记了 extras = Constants.ROUTER_EXTRA_NEED_LOGIN跳转前都会进来
			if (loginStatusService?.isLogin() == 0L) {
				//根据暴露的服务获取上次登录时间，如果是0说明未登录
				ARouter.getInstance().build("/user/loginActivity").navigation()
				return
			}
		}
		//页面未标记 extras = Constants.ROUTER_EXTRA_NEED_LOGIN 或者已经登录了直接放行
		callback?.onContinue(postcard);
	}
}