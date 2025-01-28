package com.daniel.watchsgs.util

import android.util.Log

object Logger {
    private val TAG = "WatchSGS_Logs"

    fun logd(msg : String){
        Log.d(TAG, msg)
    }

    fun logi(msg : String){
        Log.i(TAG, msg)
    }

    fun loge(msg : String){
        Log.e(TAG, msg)
    }
}