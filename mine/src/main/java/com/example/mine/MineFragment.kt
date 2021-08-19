package com.example.mine

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.example.commonlib.base.BaseFragment
import com.example.commonlib.service.LoginStatusService
import com.example.mine.databinding.FragmentMineBinding

@Route(path = "/mine/mineFragment")
class MineFragment : BaseFragment<FragmentMineBinding>() {
	
	override fun doOnViewCreated() {
		ARouter.getInstance().inject(this)
		viewBinding.userinfo.setOnClickListener {
			ARouter.getInstance().build("/user/userinfo").navigation()
		}
	}
	
	@JvmField
	@Autowired(name = "/user/loginService")
	var loginStatus: LoginStatusService? = null
	
	override fun onResume() {
		super.onResume()
		val l = loginStatus?.isLogin() ?: 0
		viewBinding.lasttime.text = if (l == 0L) "未登录" else "已登录，上次登录时间$l"
	}
}