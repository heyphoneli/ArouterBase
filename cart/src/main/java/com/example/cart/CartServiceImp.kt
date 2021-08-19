package com.example.cart

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.SPUtils
import com.example.commonlib.service.CartService

@Route(path = "/cart/cartService")
class CartServiceImp : CartService {
	/**
	 * 模拟加入购物车
	 */
	override fun add() {
		SPUtils.getInstance().put(CartConstants.CART_SP_PRODUCT_NUM, SPUtils.getInstance().getInt(CartConstants.CART_SP_PRODUCT_NUM) + 1)
	}
	
	override fun min() {
		SPUtils.getInstance().put(CartConstants.CART_SP_PRODUCT_NUM, SPUtils.getInstance().getInt(CartConstants.CART_SP_PRODUCT_NUM) - 1)
	}
	
	override fun cartNum(): Int {
		return SPUtils.getInstance().getInt(CartConstants.CART_SP_PRODUCT_NUM)
	}
	
	override fun init(context: Context?) {
	}
}