package com.example.clonecoding.utils

import android.app.Activity
import com.example.clonecoding.common.LoadingDialog

class AsyncUtils {
    companion object {
        fun callSyncWithLoading(activity: Activity, syncFunction: () -> Unit) {
            val loading = LoadingDialog(activity)
            loading.openLoading()
            syncFunction()
            loading.closeLoading()
        }
//        fun callAsyncWithLoading(activity: Activity, asyncFunction: Coroutine) {
//            val loading = LoadingDialog(activity)
//            loading.openLoading()
//            asyncFunction()
//            loading.closeLoading()
//        }
    }
}