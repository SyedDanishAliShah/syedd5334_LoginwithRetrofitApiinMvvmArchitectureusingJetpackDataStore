package com.example.loginwithretrofitinmvvmarchitecture.ui

import android.app.Activity
import android.content.Intent
import android.view.View

fun<A : Activity> Activity.startNewActivity(activity : Class<A>){
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

}//a generic function made for starting a new activity


fun View.visible(isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean){
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f

}