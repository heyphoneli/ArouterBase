package com.example.cart

import android.os.Bundle
import com.example.cart.databinding.ActivityCartBinding
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.base.BaseViewModel

class CartActivity : BaseActivity<ActivityCartBinding,BaseViewModel>() {
	override fun doOnCreate(savedInstanceState: Bundle?) {
		supportFragmentManager.beginTransaction().add(R.id.container, CartFragment()).commit()
	}
}