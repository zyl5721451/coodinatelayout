package com.yilong.redcycleview

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.view.View
import android.support.v4.view.ViewCompat
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPropertyAnimatorListener
import android.util.AttributeSet


/**
 * Created by allenzhang on 2019-12-02.
 */
class SnackBarBehavior: CoordinatorLayout.Behavior<View> {
    private var mTranslationY: Float = 0.toFloat()

    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)



    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is Snackbar.SnackbarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        if (dependency is Snackbar.SnackbarLayout) {
            this.updateTranslation(parent, child, dependency)
        }

        return false
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout, child: View, dependency: View) {
        if (dependency is Snackbar.SnackbarLayout) {
            this.updateTranslation(parent, child, dependency)
        }
    }

    private fun updateTranslation(parent: CoordinatorLayout, child: View, dependency: View) {
        val translationY = this.getTranslationY(parent, child)
        if (translationY != this.mTranslationY) {
            ViewCompat.animate(child)
                    .cancel()
            if (Math.abs(translationY - this.mTranslationY) == dependency.height.toFloat()) {
                ViewCompat.animate(child)
                        .translationY(translationY)
                        .setListener(null as ViewPropertyAnimatorListener?)
            } else {
                ViewCompat.setTranslationY(child, translationY)
            }

            this.mTranslationY = translationY
        }

    }

    private fun getTranslationY(parent: CoordinatorLayout, child: View): Float {
        var minOffset = 0.0f
        val dependencies = parent.getDependencies(child)
        var i = 0

        val z = dependencies.size
        while (i < z) {
            val view = dependencies[i] as View
            if (view is Snackbar.SnackbarLayout && parent.doViewsOverlap(child, view)) {
                minOffset = Math.min(minOffset, ViewCompat.getTranslationY(view) - view.getHeight().toFloat())
            }
            ++i
        }

        return minOffset
    }


}