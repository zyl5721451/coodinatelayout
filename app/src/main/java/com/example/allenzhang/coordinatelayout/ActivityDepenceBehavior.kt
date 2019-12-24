package com.example.allenzhang.coordinatelayout

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.yilong.coodinate.adater.ItemAdapter
import kotlinx.android.synthetic.main.activity_custom_behavior.*
import kotlinx.android.synthetic.main.activity_custom_behavior.my_list
import kotlinx.android.synthetic.main.activity_depence_behavior.*
import java.util.ArrayList

/**
 * Created by allenzhang on 2019-12-05.
 */
class ActivityDepenceBehavior : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_depence_behavior)
        var recycleview = my_list
        var linearLayoutManager = LinearLayoutManager(this)
        recycleview.layoutManager = linearLayoutManager
        var datas = ArrayList<String>()
        for (i in 0..160) {
            datas.add("item:$i")
        }
        var itemAdapter = ItemAdapter(this,datas)
        recycleview.adapter = itemAdapter
//        appbar_layout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener{
//            override fun onOffsetChanged(p0: AppBarLayout?, p1: Int) {
//                Log.d("chao","onOffsetChanged:"+p1)
//            }
//
//        })
        Log.d("chao","onOffsetChanged3:")
    }
}