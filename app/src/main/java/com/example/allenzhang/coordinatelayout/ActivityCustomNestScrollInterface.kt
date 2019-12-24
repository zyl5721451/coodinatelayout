package com.example.allenzhang.coordinatelayout

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.FragmentActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_depence_behavior.*

/**
 * Created by allenzhang on 2019-12-11.
 */
class ActivityCustomNestScrollInterface : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_depence_behavior)
        appbar_layout.addOnOffsetChangedListener(object :AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(p0: AppBarLayout?, p1: Int) {
                Log.d("chao","onOffsetChanged:"+p1)
            }

        })
        Log.d("chao","onOffsetChanged3:")

    }
}
