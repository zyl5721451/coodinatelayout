package com.example.allenzhang.coordinatelayout

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import com.yilong.coodinate.adater.ItemAdapter
import kotlinx.android.synthetic.main.activity_custom_behavior.*
import java.util.ArrayList

/**
 * Created by allenzhang on 2019-12-26.
 */
class ActivityCustomAppbarLayoutBehavior : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_applayout_behavior)
        var recycleview = my_list
        var linearLayoutManager = LinearLayoutManager(this)
        recycleview.layoutManager = linearLayoutManager
        var datas = ArrayList<String>()
        for (i in 0..280) {
            datas.add("item:$i")
        }
        var itemAdapter = ItemAdapter(this,datas)
        recycleview.adapter = itemAdapter
    }
}
