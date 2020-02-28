package com.app.weatherapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.app.weatherapp.ui.helpers.ProgressHandler

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private lateinit var progressHandler: ProgressHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressHandler = ProgressHandler(this, false)
    }

    fun showLoading(cancelable: Boolean = progressHandler.isCancelable()) {
        progressHandler.show(cancelable)
    }

    internal fun isShowing(): Boolean = progressHandler.isShowing()

    fun hideLoading() {
        progressHandler.hide()
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && isShowing()) {
            if (progressHandler.isCancelable()) {
                hideLoading()
            }
            return true
        }
        return super.onKeyUp(keyCode, event)
    }
}