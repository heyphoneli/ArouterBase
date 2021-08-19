package com.example.user

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.utils.Constants
import com.example.user.databinding.ActivityUserInfoBinding

@Route(path = "/user/userinfo", extras = Constants.ROUTER_EXTRA_NEED_LOGIN)
class UserInfoActivity : BaseActivity<ActivityUserInfoBinding>() {
	override fun doOnCreate(savedInstanceState: Bundle?) {
	}
}