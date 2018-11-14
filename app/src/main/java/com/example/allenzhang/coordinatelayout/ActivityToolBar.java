package com.example.allenzhang.coordinatelayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.yilong.coodinate.adater.ItemAdapter;
import com.yilong.redcycleview.DividerDecorateion;

import java.util.ArrayList;

/**
 * Created by allenzhang on 2018/11/12.
 */
public class ActivityToolBar extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        ViewHolder viewHolder = new ViewHolder(getWindow().getDecorView().getRootView());
    }

    public static class ViewHolder {
        private Context mContext;
        public View rootView;
        public Toolbar mToolbar;
        public AppBarLayout mAppbarLayout;
        public RecyclerView mRecycleListview;
        public CoordinatorLayout mCoordinatorLayout;
        public ArrayList<String> mDatas;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mContext = rootView.getContext();
            this.mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
            this.mAppbarLayout = (AppBarLayout) rootView.findViewById(R.id.appbar_layout);
            this.mRecycleListview = (RecyclerView) rootView.findViewById(R.id.recycle_listview);
            this.mCoordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.coordinator_layout);

            mDatas = new ArrayList<>();
            for(int i=0;i<60;i++) {
                mDatas.add("item:"+i);
            }
            ItemAdapter itemAdapter = new ItemAdapter(mContext,mDatas);


            mRecycleListview.setAdapter(itemAdapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            Log.d("chao","LinearLayoutManager:"+linearLayoutManager.canScrollHorizontally()+":"+linearLayoutManager.canScrollVertically());
            linearLayoutManager.scrollToPosition(5);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecycleListview.setLayoutManager(linearLayoutManager);
            mRecycleListview.addItemDecoration(new DividerDecorateion(mContext,LinearLayoutManager.HORIZONTAL));

//            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
//            mRecycleListview.setLayoutManager(gridLayoutManager);

//            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//            mRecycleListview.setLayoutManager(staggeredGridLayoutManager);

        }

    }
}
