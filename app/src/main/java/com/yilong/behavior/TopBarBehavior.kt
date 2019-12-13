package com.yilong.behavior

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View



/**
 * Created by allenzhang on 2019-12-11.
 */
class TopBarBehavior : AppBarLayout.Behavior {
    constructor() : super() {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}


//    override fun onStartNestedScroll(parent: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {
//        return nestedScrollAxes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
//    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }
}
