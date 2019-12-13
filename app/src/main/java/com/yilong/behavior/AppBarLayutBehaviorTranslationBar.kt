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
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator


/**
 * Created by allenzhang on 2019-12-06.
 */
class AppBarLayutBehaviorTranslationBar : AppBarLayout.Behavior {
    private var interfceptDistance: Float = 0.0f
    private var mLastY: Float = 0.0f
    private var mIsRestart: Boolean = true
    private lateinit var mTextView: TextView
    private val INTERPOLATOR = AccelerateInterpolator()
    private var viewY: Float = 0.toFloat()//控件距离coordinatorLayout底部距离
    private var isAnimate: Boolean = false//动画是否在进行
    private var mcontext: Context? = null
    private var mNavigationHeight: Int = 0
    private var mTvTitleHeight = 0
    private lateinit var mTvTitle: TextView
    private val TARGET_HEIGHT = 500f // 最大滑动距离
    private var mTotalDy: Float = 0.toFloat()     // 总滑动的像素数
    private var mLastScale: Float = 0.toFloat()   // 最终放大比例
    private var mLastBottom: Int = 0    // AppBarLayout的最终Bottom值
    private val TAG = AppBarLayutBehaviorTranslationBar::class.java.simpleName

    private lateinit var mTargetView: View     // 目标View
    private var mParentHeight: Int = 0      // AppBarLayout的初始高度
    private var mTargetViewHeight: Int = 0  // 目标View的高度
    var mYDistance = 0

    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        mcontext = context
    }


    //dp转化成px
    fun dp2Px(dp: Float): Int {
        val scale = mcontext?.resources?.displayMetrics?.density
        if (scale == null) {
            return dp.toInt()
        }
        return (dp * scale + 0.5f).toInt()
    }

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: AppBarLayout, ev: MotionEvent): Boolean {

        when (ev.action){
            MotionEvent.ACTION_DOWN ->{
//                mYDistance = 0
                mLastY = ev.y
//                Log.d("chao","onInterceptTouchEvent down:"+(ev.y - mLastY))

            }
            MotionEvent.ACTION_MOVE ->{
//                Log.d("chao","onInterceptTouchEvent move:"+(ev.y - mLastY))
                interfceptDistance = (ev.y - mLastY)

            }

            MotionEvent.ACTION_CANCEL ->{
//                Log.d("chao","onInterceptTouchEvent cancle:"+(ev.y - mLastY))
            }

            MotionEvent.ACTION_UP ->{
//                Log.d("chao","onInterceptTouchEvent up:"+(ev.y - mLastY))
            }

        }
        return super.onInterceptTouchEvent(parent, child, ev)
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: AppBarLayout, ev: MotionEvent): Boolean {
        when (ev.action){
            MotionEvent.ACTION_DOWN ->{
                mLastY = ev.y
//                Log.d("chao","onTouchEvent down:"+(ev.y - mLastY))

            }
            MotionEvent.ACTION_MOVE ->{
//                Log.d("chao","onTouchEvent move:"+(ev.y - mLastY))

            }

            MotionEvent.ACTION_CANCEL ->{
//                Log.d("chao","onTouchEvent cancle:"+(ev.y - mLastY))
            }

            MotionEvent.ACTION_UP ->{
//                Log.d("chao","onTouchEvent up:"+(ev.y - mLastY))
            }

        }
        return super.onTouchEvent(parent, child, ev)
    }



//    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
//        if (mTargetView != null && ((dy < 0 && child.getBottom() >= mParentHeight) || (dy > 0 && child.getBottom() > mParentHeight))) {
//            scale(child,target,dy)
//       }else {
//           super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
//       }


//        Log.d(TAG,"onNestedPreScroll:向下"+target.top+":"+target.bottom+":"+child.top+":"+child.bottom+":"+child.y)
//        if(dy<0){//上滑
//            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
//            if(target.top<=mTvTitleHeight){
//                ViewCompat.setTranslationY(coordinatorLayout,mTvTitleHeight.toFloat()*2)
//            }else {
//                ViewCompat.setTranslationY(coordinatorLayout,0.0f)
////                child.bottom = mNavigationHeight+ 3*mTvTitleHeight
//                target.scrollY = 0
//            }
//        }else if(dy>0){//下滑
//            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
//            if(target.top>=mTvTitleHeight){
//                ViewCompat.setTranslationY(coordinatorLayout,0.0f)
////                child.bottom = mNavigationHeight+ 3*mTvTitleHeight
//                target.scrollY = 0
//            }else {
//                ViewCompat.setTranslationY(coordinatorLayout,mTvTitleHeight.toFloat()*2)
//            }
//        }else {
//            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
//        }
//    }

//    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, abl: AppBarLayout, target: View, type: Int) {
//        if(mTotalDy>0){
//            mTotalDy = 0.0f
//        }
//        super.onStopNestedScroll(coordinatorLayout, abl, target, type)
//    }

    override fun layoutChild(parent: CoordinatorLayout?, child: AppBarLayout?, layoutDirection: Int) {
        super.layoutChild(parent, child, layoutDirection)
        mTextView = child!!.findViewById<TextView>(R.id.title)

        mNavigationHeight = (parent?.parent as ViewGroup).findViewById<ImageView>(R.id.iv_navigationImage).measuredHeight
//        Log.d("chao", "layoutChild：" + mNavigationHeight)
    }

    override fun onStartNestedScroll(parent: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {
//        Log.d("chao","onStartNestedScroll:")
        return (nestedScrollAxes and ViewCompat.SCROLL_AXIS_VERTICAL) != 0
    }

//    override fun onNestedPreFling(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, velocityX: Float, velocityY: Float): Boolean {
//
////        var fline = super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
////        Log.d("chao","onNestedPreFling:"+velocityY)
//        if(abs(velocityY)>1000){
////            mIsRestart = true
//        }
//        return false
//    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
//        Log.d("chao","onNestedScroll:"+type+":"+dyConsumed+":"+dyUnconsumed)
    }

    override fun onNestedScrollAccepted(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, axes: Int, type: Int) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type)
//        Log.d("chao","onNestedScrollAccepted:")
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, abl: AppBarLayout, target: View, type: Int) {
        super.onStopNestedScroll(coordinatorLayout, abl, target, type)
        mIsRestart = true
//        Log.d("chao","onStopNestedScroll:")
    }


    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
//        animation(target, type, dy, child)
        var position = ((target as RecyclerView).layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        if (position > 0) {
            if (dy < 0 && !isAnimate) {//向下
                var valueAnimator = ValueAnimator.ofInt(topAndBottomOffset,-(child.height - mTextView.height))
                valueAnimator.setDuration(500)
                valueAnimator.addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                        //                                Log.d("chao","onNestedPreScroll:onAnimationRepeat")
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        //                                Log.d("chao","onNestedPreScroll:onAnimationEnd"+topAndBottomOffset)
                        isAnimate = false
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        //                                Log.d("chao","onNestedPreScroll:onAnimationCancel")
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        //                                Log.d("chao","onNestedPreScroll:onAnimationStart")
                        isAnimate = true
                    }
                })
                valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                    override fun onAnimationUpdate(animation: ValueAnimator?) {
                        var value = animation?.animatedValue as Int

                        //                                if(!mIsRestart){
                        //                                    Log.d("chao","onNestedPreScroll:onAnimationUpdate"+value)
                        topAndBottomOffset = value
                        //                                }

                    }

                })
                valueAnimator.start()
            } else if (dy > 0 && !isAnimate) {//向上
//                Log.d("chao", "onNestedPreScroll:up" + topAndBottomOffset)
                var valueAnimator = ValueAnimator.ofInt(topAndBottomOffset, -child.height)
                valueAnimator.setDuration(500)
                valueAnimator.addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                        //                                Log.d("chao","onNestedPreScroll:onAnimationRepeat")
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        //                                Log.d("chao","onNestedPreScroll:onAnimationEnd")
                        isAnimate = false
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        //                                Log.d("chao","onNestedPreScroll:onAnimationCancel")
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        //                                Log.d("chao","onNestedPreScroll:onAnimationStart")
                        isAnimate = true
                    }
                })
                valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                    override fun onAnimationUpdate(animation: ValueAnimator?) {
                        var value = animation?.animatedValue as Int

                        //                                if(!mIsRestart){
                        //                                    Log.d("chao","onNestedPreScroll:onAnimationUpdate"+value)
                        topAndBottomOffset = value
                        //                                }
                    }

                })
                valueAnimator.start()
            }

        }


    }

    private fun animation(target: View, type: Int, dy: Int, child: AppBarLayout) {
        if (target is RecyclerView) {
            if (type == ViewCompat.TYPE_TOUCH) {
                var recyclerView = target as RecyclerView
    //                if(recyclerView.y<=mTextView.height){
    //                mYDistance +=dy
    //                Log.d("chao","onNestedPreScroll:"+mYDistance)
                if (dy > 0) {//上滑
                    if (mYDistance < 0) {
                        mYDistance = 0
                    }
                    mYDistance += dy
    //                    mYDistance = Math.max(mYDistance,0)
    //                    Log.d("chao","onNestedPreScrollup:"+mYDistance)
                } else {//下滑
                    if (mYDistance > 0) {
                        mYDistance = 0
                    }
                    mYDistance += dy
    //                    mYDistance = Math.min(mYDistance,2*mTextView.height)
    //                    Log.d("chao","onNestedPreScrolldown:"+mYDistance)

                }
    //                Log.d("chao","onNestedPreScroll:"+mYDistance)
                if (dy < 0 && !isAnimate && child.bottom != (mNavigationHeight + mTextView.height) && mIsRestart) {
    //                        Log.d("chao","onNestedPreScroll:down"+topAndBottomOffset+":"+-(child.height - mNavigationHeight - mTextView.height))
                    var valueAnimator = ValueAnimator.ofInt(topAndBottomOffset, -(child.height - mNavigationHeight - mTextView.height))
                    valueAnimator.setDuration(500)
                    valueAnimator.addListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) {
    //                                Log.d("chao","onNestedPreScroll:onAnimationRepeat")
                        }

                        override fun onAnimationEnd(animation: Animator?) {
    //                                Log.d("chao","onNestedPreScroll:onAnimationEnd"+topAndBottomOffset)
                            isAnimate = false
                        }

                        override fun onAnimationCancel(animation: Animator?) {
    //                                Log.d("chao","onNestedPreScroll:onAnimationCancel")
                        }

                        override fun onAnimationStart(animation: Animator?) {
                            mIsRestart = false
    //                                Log.d("chao","onNestedPreScroll:onAnimationStart")
                            isAnimate = true
                        }
                    })
                    valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                        override fun onAnimationUpdate(animation: ValueAnimator?) {
                            var value = animation?.animatedValue as Int

    //                                if(!mIsRestart){
    //                                    Log.d("chao","onNestedPreScroll:onAnimationUpdate"+value)
                            topAndBottomOffset = value
    //                                }

                        }

                    })
                    valueAnimator.start()
                } else if (dy > 0 && !isAnimate) {
    //                        topAndBottomOffset = -(child.height - mNavigationHeight - mTextView.height)
    //                        Log.d("chao","onNestedPreScroll:up"+topAndBottomOffset)
    //                        var valueAnimator = ValueAnimator.ofInt(-(child.height - mNavigationHeight - mTextView.height),-(child.height - mTextView.height))
    //                        valueAnimator.setDuration(500)
    //                        valueAnimator.addListener(object :Animator.AnimatorListener{
    //                            override fun onAnimationRepeat(animation: Animator?) {
    ////                                Log.d("chao","onNestedPreScroll:onAnimationRepeat")
    //                            }
    //
    //                            override fun onAnimationEnd(animation: Animator?) {
    ////                                Log.d("chao","onNestedPreScroll:onAnimationEnd")
    //                                isAnimate = false
    //                            }
    //
    //                            override fun onAnimationCancel(animation: Animator?) {
    ////                                Log.d("chao","onNestedPreScroll:onAnimationCancel")
    //                            }
    //
    //                            override fun onAnimationStart(animation: Animator?) {
    ////                                Log.d("chao","onNestedPreScroll:onAnimationStart")
    //                                mIsRestart = false
    //                                isAnimate = true
    //                            }
    //                        })
    //                        valueAnimator.addUpdateListener(object :ValueAnimator.AnimatorUpdateListener{
    //                            override fun onAnimationUpdate(animation: ValueAnimator?) {
    //                                var value = animation?.animatedValue as Int
    //
    ////                                if(!mIsRestart){
    ////                                    Log.d("chao","onNestedPreScroll:onAnimationUpdate"+value)
    //                                    topAndBottomOffset = value
    ////                                }
    //                            }
    //
    //                        })
    //                        valueAnimator.start()
                }
    //                }
            }

        }
    }

    private fun scale(abl: AppBarLayout, target: View, dy: Int) {
        mTotalDy += -dy
        mTotalDy = Math.min(mTotalDy, TARGET_HEIGHT)
        mLastScale = Math.max(1f, 1f + mTotalDy / TARGET_HEIGHT)
        ViewCompat.setScaleX(mTargetView, mLastScale)
        ViewCompat.setScaleY(mTargetView, mLastScale)
        mLastBottom = mParentHeight + (mTargetViewHeight / 2 * (mLastScale - 1)).toInt()
        abl.bottom = mLastBottom
        target.scrollY = 0
    }

    //隐藏时的动画
    private fun hide(view: View, y: Float) {
        val animator = view.animate().translationY(y).setInterpolator(INTERPOLATOR).setDuration(500)

        animator.setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                isAnimate = true
            }

            override fun onAnimationEnd(animator: Animator) {
                view.visibility = View.INVISIBLE
                isAnimate = false
            }

            override fun onAnimationCancel(animator: Animator) {
            }

            override fun onAnimationRepeat(animator: Animator) {}
        })
        animator.start()
    }

    //显示时的动画
    fun show(view: View, y: Float) {
        val animator = view.animate().translationY(y).setInterpolator(INTERPOLATOR).setDuration(500)
        animator.setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                view.visibility = View.VISIBLE
                isAnimate = true
            }

            override fun onAnimationEnd(animator: Animator) {
                isAnimate = false
            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {}
        })
        animator.start()
    }


}