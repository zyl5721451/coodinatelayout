package com.yilong.behavior

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.allenzhang.coordinatelayout.R
import kotlinx.android.synthetic.main.activity_depence_behavior.view.*
import kotlin.math.abs

/**
 * Created by allenzhang on 2019-12-05.
 */
class RecyclerViewBehavior :CoordinatorLayout.Behavior<View>{
    private var mPublishDistance: Float = 0.0f
    private var mTextHeight: Int = 0
    private val TAG = RecyclerViewBehavior::class.java.simpleName
    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is AppBarLayout
    }

    override fun onLayoutChild(parent: CoordinatorLayout, child: View, layoutDirection: Int): Boolean {
        var layout =  super.onLayoutChild(parent, child, layoutDirection)
        mTextHeight = parent.findViewById<TextView>(R.id.title).measuredHeight
        mPublishDistance = (parent.height - (parent.parent as ViewGroup).findViewById<ImageView>(R.id.iv_bottom).y)
        Log.d(TAG,"onLayoutChild height:${mPublishDistance} translationY${(parent.parent as ViewGroup).findViewById<ImageView>(R.id.iv_bottom).y}")
        return layout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {

        var dependencyTop = abs(dependency.top)
        var navigationHeight = dependency.height - mTextHeight
        if(dependencyTop>=navigationHeight&&dependencyTop<=dependency.height){
            var ratio = (dependency.height - dependencyTop)/mTextHeight.toFloat()
            child.translationY = (1-ratio)*child.height
            Log.d(TAG,"onDependentViewChanged height:${ratio} translationY${(1-ratio)*child.height}")
        }
        return true
    }
}