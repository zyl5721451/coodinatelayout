package com.yilong.behavior

import android.animation.Animator
import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import com.example.allenzhang.coordinatelayout.R
import android.animation.ValueAnimator
import android.opengl.ETC1.getHeight
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import kotlinx.android.synthetic.main.activity_custom_behavior.view.*


/**
 * Created by allenzhang on 2019-12-06.
 */
class AppBarLayutBehavior : AppBarLayout.Behavior {
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
    private val TAG = AppBarLayutBehavior::class.java.simpleName

    private lateinit var mTargetView: View     // 目标View
    private var mParentHeight: Int = 0      // AppBarLayout的初始高度
    private var mTargetViewHeight: Int = 0  // 目标View的高度
    var mYDistance = 0

    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        mcontext = context
    }


    override fun onLayoutChild(parent: CoordinatorLayout, abl: AppBarLayout, layoutDirection: Int): Boolean {
        var handled = super.onLayoutChild(parent, abl, layoutDirection)
        // 需要在调用过super.onLayoutChild()方法之后获取
        mTargetView = parent.findViewById(R.id.iv_bg)


        mTvTitle = parent.findViewById<TextView>(R.id.title)
        mTargetViewHeight = mTargetView.height
        mTvTitleHeight = mTvTitle.height
        mNavigationHeight = abl.height
        return handled
    }

    //dp转化成px
    fun dp2Px(dp: Float): Int {
        val scale = mcontext?.resources?.displayMetrics?.density
        if (scale == null) {
            return dp.toInt()
        }
        return (dp * scale + 0.5f).toInt()
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
        Log.d("chao", "layoutChild：" + mNavigationHeight)
    }

    override fun onStartNestedScroll(parent: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {

        return (nestedScrollAxes and ViewCompat.SCROLL_AXIS_VERTICAL) != 0
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        var posi = (((target as RecyclerView).layoutManager) as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
//        Log.d("chao","onNestedPreScroll:"+child.y+":"+child.translationY)
            if (posi > 0) {
                if (dy > 0 && child.translationY != 0.0f && !isAnimate) {//向上滑动
//                    child.scrollTo(0,-child.height+dp2Px(50.0f))
//                    child.bottom = dp2Px(50.0f)
//                    hide(child,-(child.bottom - dp2Px(50.0f)).toFloat())
//                    Log.d("chao", "onNestedPreScroll:hide")
                    child.translationY = 0.0f
//                    child.scrollTo(0,child.height)
                    mTotalDy = 0.0f

                } else if (dy < 0 && !isAnimate) {//向下滑动
//                    child.scrollTo(0,child.height/2)
//                    child.translationY = child.height/2.0f

//                    child.bottom = dp2Px(150.0f)
//                    show(child,(dp2Px(150.0f) - child.bottom).toFloat())
                    child.translationY = 2 * mTextView.height.toFloat()
//                    Log.d("chao", "onNestedPreScroll:show")
                    mTotalDy = 0.0f
                }

//
//                if (dy > 0) {//上滑
////                    if(mYDistance<0){
////                        mYDistance = 0
////                    }
//                    mYDistance += -dy
//                    mYDistance = Math.max(mYDistance, 0)
//                    child.translationY = mYDistance.toFloat()
//
//                } else {//下滑
////                    if(mYDistance>0){
////                        mYDistance = 0
////                    }
//                    mYDistance += -dy
//                    mYDistance = Math.min(mYDistance, child.height)
//                    child.translationY = mYDistance.toFloat()
//                }

            } else {

                if (child.translationY != 0.0f) {
                    if (dy < 0) {//向下滑
                        var downY = Math.max(child.translationY+dy, 0.0f)
                        child.translationY = downY
                        Log.d("chao", "onNestedPreScrolldown:" + (downY))
                    } else if(dy > 0) {//向上滑
                        var upY = Math.min(child.translationY+dy, (2*mTextView.height).toFloat())
                        child.translationY = upY
                        Log.d("chao", "onNestedPreScrollup:" + (upY))
                    }
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