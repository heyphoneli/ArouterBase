package com.example.cart

import android.os.Bundle
import com.example.cart.databinding.ActivityCartBinding
import com.example.commonlib.base.BaseActivity

class CartActivity : BaseActivity<ActivityCartBinding>() {
	override fun doOnCreate(savedInstanceState: Bundle?) {
		supportFragmentManager.beginTransaction().add(R.id.container, CartFragment()).commit()
	}
}