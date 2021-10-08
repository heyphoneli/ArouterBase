package com.example.user

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.SPUtils
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.base.BaseViewModel
import com.example.user.databinding.ActivityLoginBinding

@Route(path = "/user/loginActivity")
class LoginActivity : BaseActivity<ActivityLoginBinding,BaseViewModel>() {
	override fun doOnCreate(savedInstanceState: Bundle?) {
		viewBinding.button.setOnClickListener {
			SPUtils.getInstance().put(UserConstants.SP_LOGIN_TIME, System.currentTimeMillis())
			finish()
		}
	}
}