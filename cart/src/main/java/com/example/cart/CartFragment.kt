package com.example.cart

import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.SPUtils
import com.example.cart.databinding.FragmentCartBinding
import com.example.commonlib.base.BaseFragment

@Route(path = "/cart/cartFragment")
class CartFragment : BaseFragment<FragmentCartBinding>() {
	override fun doOnViewCreated() {
		val num = SPUtils.getInstance().getInt(CartConstants.CART_SP_PRODUCT_NUM)
		for (i in 0 until num) {
			add()
		}
		viewBinding.add.setOnClickListener {
			add()
			updateCartNum(1)
		}
		viewBinding.min.setOnClickListener {
			if (viewBinding.container.childCount == 0)
				return@setOnClickListener
			viewBinding.container.removeViewAt(0)
			updateCartNum(-1)
		}
	}
	
	/**
	 * 模拟添加购物车
	 */
	private fun add() {
		viewBinding.container.addView(TextView(requireContext()).apply {
			setBackgroundColor(Color.CYAN)
			gravity = Gravity.CENTER
			text = "product"
			layoutParams = LinearLayout.LayoutParams(-1, 250).also {
				it.bottomMargin = 15
			}
		})
	}
	
	private fun updateCartNum(i: Int) {
		SPUtils.getInstance().put(CartConstants.CART_SP_PRODUCT_NUM, SPUtils.getInstance().getInt(CartConstants.CART_SP_PRODUCT_NUM) + i)
	}
}