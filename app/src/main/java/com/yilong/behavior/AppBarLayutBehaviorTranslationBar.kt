package com.yilong.behavior

import android.animation.Animator
import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import com.example.allenzhang.coordinatelayout.R
import android.animation.ValueAnimator
import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.support.v4.math.MathUtils


/**
 * Created by allenzhang on 2019-12-06.
 */
class AppBarLayutBehaviorTranslationBar : AppBarLayout.Behavior {
    private var translationY = 0

    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onStartNestedScroll(parent: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {
//        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type)
        var textHeight = child.findViewById<TextView>(R.id.title).measuredHeight
//        topAndBottomOffset = textHeight
        Log.d("chao","onStartNestedScroll:"+":"+child.top+":"+textHeight)
        return nestedScrollAxes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onLayoutChild(parent: CoordinatorLayout, abl: AppBarLayout, layoutDirection: Int): Boolean {
        var layout = super.onLayoutChild(parent, abl, layoutDirection)

        return layout
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: AppBarLayout, ev: MotionEvent): Boolean {
//        setTopAndBottomOffset(137)
        return super.onTouchEvent(parent, child, ev)
    }

    /**
     * 向下滑动时，dyunconsume是负值。向上滑动时，没有调用
     */
    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
        Log.d("chao", "onNestedScroll:" + dyConsumed + ":" + dyUnconsumed)
    }

    /**
     * consum是appbarlayout的消费量，向下滑动时，一直是0，向上滑动时，appbarlayout的变动为正，consume一直是正。
     */
    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        var textHeight = child.findViewById<TextView>(R.id.title).measuredHeight
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        var navigationHeight = 275
        translationY = translationY-dy
        Log.d("chao","onNestedPreScroll:"+":"+child.top+":"+(navigationHeight)+":"+consumed[1])
        if(translationY>textHeight){
            translationY = textHeight
        }else if(translationY<0){
            translationY = 0
        }
        if(target.top>navigationHeight){
            translationY = 0
        }
//        child.top = child.top+translationY
//        child.layoutParams as
//        child.translationY = translationY.toFloat()
//        consumed[1] = translationY+consumed[1]
//        child.scrollTo(0,translationY)
//       ViewCompat.offsetTopAndBottom(child,translationY)
//
//        original(dy, child, consumed, coordinatorLayout, type, target)


    }

    private fun original(dy: Int, child: AppBarLayout, consumed: IntArray, coordinatorLayout: CoordinatorLayout, type: Int, target: View) {
//        if (dy != 0) {
//            val min: Int
//            val max: Int
//            if (dy < 0) {
//                min = -child.totalScrollRange
//                max = min + ViewCompat.getMinimumHeight(child)
//            } else {
//                min = -child.totalScrollRange
//                max = 0
//            }
//            if (min != max) {
//                consumed[1] = setHeaderTopBottomOffset(coordinatorLayout, child, topAndBottomOffset - dy, min, max)
//
//                if (type == 1) {
//                    val curOffset = topAndBottomOffset
//                    if (dy < 0 && curOffset == 0 || dy > 0 && curOffset == -child.getDownNestedScrollRange()) {
//                        ViewCompat.stopNestedScroll(target, 1)
//                    }
//                }
//            }
//        }
    }

//    fun setHeaderTopBottomOffset(coordinatorLayout: CoordinatorLayout?, appBarLayout: AppBarLayout?, newOffset: Int, minOffset: Int, maxOffset: Int): Int {
//        var newOffset = newOffset
//        val curOffset = this.topAndBottomOffset
//        var consumed = 0
//        if (minOffset != 0 && curOffset >= minOffset && curOffset <= maxOffset) {
//            newOffset = MathUtils.clamp(newOffset, minOffset, maxOffset)
//            if (curOffset != newOffset) {
//                topAndBottomOffset = newOffset
//                consumed = curOffset - newOffset
//            }
//        }
//        return consumed
//    }


}