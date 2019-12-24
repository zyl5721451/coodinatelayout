package com.yilong.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.allenzhang.coordinatelayout.R

/**
 * Created by allenzhang on 2019-12-18.
 */
class MaskRecommendBehavior :CoordinatorLayout.Behavior<View>{
    private var translationY = 0
    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    override fun onLayoutChild(parent: CoordinatorLayout, child: View, layoutDirection: Int): Boolean {
        var layout = super.onLayoutChild(parent, child, layoutDirection)
        return layout
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
//        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type)
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL !=0
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
        Log.d("chao","onNestedScroll:"+":"+dyConsumed+":"+dyUnconsumed)
    }
    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        Log.d("chao","onNestedPreScroll:"+target.top+":"+translationY+":"+consumed[1])
        var navigationHeight = 275
        translationY = translationY-dy
//        Log.d("chao","onNestedPreScroll:"+target.top+":"+translationY+":"+(navigationHeight - child.height))
        if(translationY>0){
            translationY = 0
        }else if(translationY<-child.height){
            translationY = -child.height
        }
        if(target.top>navigationHeight+child.height){
            translationY = -child.height
        }
        child.translationY = translationY.toFloat()

    }
}