package com.example.commonlib.service

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * 购物车服务
 */
interface CartService : IProvider {
	/**
	 * 添加购物车
	 */
	fun add()
	
	/**
	 * 删除购物车
	 */
	fun min()
	
	/**
	 * 购物车商品数量
	 */
	fun cartNum(): Int
}