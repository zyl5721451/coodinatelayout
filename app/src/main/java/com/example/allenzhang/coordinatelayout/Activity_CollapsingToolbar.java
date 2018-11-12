package com.example.allenzhang.coordinatelayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yilong.coodinate.adater.ItemAdapter;

import java.util.ArrayList;

/**
 * Created by allenzhang on 2018/11/12.
 */
public class Activity_CollapsingToolbar extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
        ViewHolder viewHolder = new ViewHolder(getWindow().getDecorView().getRootView());
    }

    public static class ViewHolder {
        private Context mContext;
        public View rootView;
        public ImageView mIvImage;
        public TabLayout mTabs;
        public RecyclerView mRecycleListview;
        public ArrayList<String> mDatas;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mContext = rootView.getContext();
            this.mIvImage = (ImageView) rootView.findViewById(R.id.iv_image);
            this.mTabs = (TabLayout) rootView.findViewById(R.id.tabs);
            this.mRecycleListview = (RecyclerView) rootView.findViewById(R.id.recycle_listview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            mRecycleListview.setLayoutManager(linearLayoutManager);

            mDatas = new ArrayList<>();
            for(int i=0;i<60;i++) {
                mDatas.add("item:"+i);
            }
            ItemAdapter adapter = new ItemAdapter(mContext,mDatas);
            mRecycleListview.setAdapter(adapter);
        }

    }
}
