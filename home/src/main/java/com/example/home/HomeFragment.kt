package com.example.home

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.example.commonlib.base.BaseFragment
import com.example.commonlib.service.CartService
import com.example.home.databinding.FragmentHomeBinding

@Route(path = "/home/homeFragment")
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
	@JvmField
	@Autowired(name = "/cart/cartService")
	var cartService: CartService? = null
	
	override fun doOnViewCreated() {
		ARouter.getInstance().inject(this)
		viewBinding.add.setOnClickListener {
			cartService?.add()
			ToastUtils.showShort("加1")
			viewBinding.tvNum.text = "购物车里有${cartService?.cartNum()}件商品"
		}
	}
	
	override fun onResume() {
		super.onResume()
		viewBinding.tvNum.text = "购物车里有${cartService?.cartNum()}件商品"
	}
}