package com.beibeilab.bubblesample

import android.app.Application
import android.util.Log

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d("badu", "Application onCreate")
    }


    override fun onTerminate() {
        super.onTerminate()

        Log.d("badu", "Application onTerminate")
    }

}