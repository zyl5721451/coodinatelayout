package com.yilong.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.support.v4.view.ViewCompat.setTranslationY
import android.opengl.ETC1.getHeight
import android.support.v4.view.ViewCompat.getTranslationY



/**
 * Created by allenzhang on 2019-12-05.
 */
class SampleHeaderBehavior :CoordinatorLayout.Behavior<View>{
    private var chileHeight: Int = 0
    private var childY: Int = 0
    private val TAG = SampleHeaderBehavior::class.java.simpleName
    var mYDistance = 0
    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
       Log.d(TAG,"onStartNestedScroll:"+axes+":"+type+"treu:"+(axes and ViewCompat.SCROLL_AXIS_VERTICAL !== 0))
        return false
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        Log.d(TAG,"onNestedPreScroll:"+":"+type)
        if(childY == 0){
            childY = child.y.toInt()
        }
        if(chileHeight == 0){
            chileHeight = child.height
        }
        if(target is RecyclerView){
            var recyclerView = target as RecyclerView
            var pos = ((recyclerView.layoutManager) as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()

//            Log.d(TAG,"onNestedPreScroll pos:${recyclerView.y}dx:${dx} dy:${dy}")
            if(recyclerView.y<3*child.height){
                child.visibility = View.VISIBLE

                if(dy>0){//上滑
//                    if(mYDistance<0){
//                        mYDistance = 0
//                    }
                        mYDistance +=-dy
                        mYDistance = Math.max(mYDistance,0)
                        child.translationY = mYDistance.toFloat()

                }else {//下滑
//                    if(mYDistance>0){
//                        mYDistance = 0
//                    }
                    mYDistance +=-dy
                    mYDistance = Math.min(mYDistance,child.height)
                    child.translationY = mYDistance.toFloat()
                }


            }else {
                child.visibility = View.INVISIBLE
            }
//            if(pos ==0&&pos<lastPosition){
//                downReach = true
//            }
//            Log.d(TAG,"onNestedPreScroll pos:${pos} lastposition:${lastPosition}")
//            if(canScroll(target,dy.toFloat())&&pos == 0){
//                var finalY = child.translationY - dy
//                if (finalY < -child.height) {
//                    finalY = -child.height.toFloat()
//                    upReach = true
//                } else if (finalY > 0) {
//                    finalY = 0f
//                }
//                child.translationY = finalY
//                // 让CoordinatorLayout消费滑动事件
//                consumed[1] = dy
//            }
//
//            lastPosition = pos
        }
    }


    override fun onNestedPreFling(coordinatorLayout: CoordinatorLayout, child: View, target: View, velocityX: Float, velocityY: Float): Boolean {

        var cons =  super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
//        Log.d(TAG,"onNestedPreFling:${velocityY}:${childY}:${chileHeight}")
//        if(velocityY>100){
//            child.scrollTo(0,childY.toInt())
//        }else {
//            child.scrollTo(0,childY.toInt()+chileHeight)
//        }

        return cons
    }



}