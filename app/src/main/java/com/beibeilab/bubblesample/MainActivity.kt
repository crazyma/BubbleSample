package com.beibeilab.bubblesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beibeilab.bubblesample.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        Log.d("badu", "MainActivity onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("badu", "MainActivity onDestroy")
    }

}
