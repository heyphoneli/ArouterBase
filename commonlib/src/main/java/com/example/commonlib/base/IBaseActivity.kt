package com.example.commonlib.base

import android.os.Bundle

interface IBaseActivity {
    fun doOnCreate(savedInstanceState: Bundle?)
    fun isImmersionEnable(): Boolean
    fun isStatusBarDarkFont(): Boolean
}