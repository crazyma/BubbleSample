package com.beibeilab.bubblesample.ui.bubble

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.beibeilab.bubblesample.R
import com.beibeilab.bubblesample.ui.bubble.apilist.ApiListFragment
import com.beibeilab.bubblesample.ui.bubble.apiop.ApiOperateFragment

class BubbleActivity : AppCompatActivity(), ApiListFragment.Interaction {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubble)
        Log.d("badu", "BubbleActivity onCreate")
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ApiListFragment.newInstance())
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("badu", "BubbleActivity onDestroy")
    }

    override fun onApiEndpointClicked() {
        jumpToApiOperatePage()
    }

    private fun jumpToApiOperatePage(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ApiOperateFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}