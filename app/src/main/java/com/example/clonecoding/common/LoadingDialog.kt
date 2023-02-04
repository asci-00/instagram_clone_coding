package com.example.clonecoding.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.os.Build
import android.view.LayoutInflater
import android.view.View.*
import android.view.ViewGroup.LayoutParams.*
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager.LayoutParams.*
import com.example.clonecoding.R

class LoadingDialog {
    private var activity: Activity? = null
    private var dialog: AlertDialog? = null

    constructor(activity: Activity) {
        this.activity = activity
    }

    @SuppressLint("InflateParams")
    fun openLoading() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        val inflater: LayoutInflater = activity!!.layoutInflater

        dialog = builder
            .setView(inflater.inflate(R.layout.common_loading, null))
            .setCancelable(false)
            .create()
        dialog?.let {
            val window = it.window!!
            window.setFlags(FLAG_NOT_FOCUSABLE, FLAG_NOT_FOCUSABLE)
            window.addFlags(FLAG_ALT_FOCUSABLE_IM or FLAG_KEEP_SCREEN_ON)

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                window.decorView.systemUiVisibility = (
                        SYSTEM_UI_FLAG_IMMERSIVE
                        or SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or SYSTEM_UI_FLAG_FULLSCREEN
                    )
            } else {
                window.setDecorFitsSystemWindows(false)
                val controller = window.insetsController
                controller?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
            window.setBackgroundDrawableResource(android.R.color.transparent)
            it.show()

            window.clearFlags(FLAG_NOT_FOCUSABLE)
        }

    }

    fun closeLoading() { dialog?.dismiss() }
}