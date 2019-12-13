package com.yilong.behavior

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.allenzhang.coordinatelayout.R

/**
 * Created by allenzhang on 2019-12-05.
 */
class SampleTitleBehavior : CoordinatorLayout.Behavior<View> {
    private val TAG = SampleTitleBehavior::class.java.simpleName
    private var deltaY = 0.0f
    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {

        return dependency is RecyclerView
    }

    override fun onLayoutChild(parent: CoordinatorLayout, child: View, layoutDirection: Int): Boolean {
        var comsume =  super.onLayoutChild(parent, child, layoutDirection)
        return comsume
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        Log.d(TAG,"onNestedPreScroll:")
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }


    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        Log.d(TAG,"onDependentViewChanged:"+dependency.y+":"+parent.y)
        if (deltaY == 0.0f) {
            deltaY = dependency.getY() - child.getHeight()//
        }
//        var position = ((dependency as RecyclerView).layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
//        Log.d(TAG,"onDependentViewChanged:"+position)
//        var dy = dependency.y - child.height
//        dy = if(dy<0) 0.0f else dy
//        var y = -(dy/deltaY)*child.height
//        var alpha = 1-dy/deltaY
//        child.translationY = y
//        child.alpha = alpha
        if(dependency.y>=0){
            child.visibility = View.GONE
        }else {
            child.visibility = View.VISIBLE
        }



        return true
    }



}