package com.yilong.redcycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by allenzhang on 2018/11/14.
 */
public class DividerDecorateion extends RecyclerView.ItemDecoration {
    @OritationType
    private int mOritationType;
    private Drawable mDivider;
    private int[] attrs = new int[]{
            android.R.attr.listDivider
    };
    public DividerDecorateion(Context context,@OritationType int oritation) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
        setmOritationType(oritation);
    }

    private void setmOritationType(@OritationType int oritaiton) {
        if (oritaiton != LinearLayoutManager.VERTICAL && oritaiton != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("参数不合法");
        }
        mOritationType = oritaiton;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if(mOritationType == LinearLayoutManager.VERTICAL) {
            drawVerticle(c,parent);
        }else {
            drawHOrizontal(c,parent);
        }
    }

    private void drawHOrizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight()- parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight()+params.rightMargin+Math.round(child.getTranslationX());
            int right = left+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }

    }

    private void drawVerticle(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int count = parent.getChildCount();
        for(int i=0;i<count;i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom()+params.bottomMargin+Math.round(child.getTranslationY());
            int bottom = top+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        Log.d("chao","getItemOffsets:"+outRect.toShortString());
        if(mOritationType == LinearLayoutManager.VERTICAL) {
            outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        }else {
            outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }
    }

    @IntDef({LinearLayoutManager.VERTICAL,LinearLayoutManager.HORIZONTAL})
    public @interface OritationType{

    }
}
