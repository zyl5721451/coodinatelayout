package com.yilong.coodinate.adater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.allenzhang.coordinatelayout.R;

import java.util.ArrayList;

/**
 * Created by allenzhang on 2018/11/12.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Context mContext;
    private ArrayList<String> mDatas;

    public ItemAdapter(Context mContext, ArrayList<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(View.inflate(mContext,R.layout.item_adapter,null));
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.mTvText.setText(mDatas.get(i));
    }

    @Override
    public int getItemCount() {
        if(null!=mDatas&&mDatas.size()>0) {
            return mDatas.size();
        }
        return 0;
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView mTvText;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvText = (TextView)itemView.findViewById(R.id.tv_text);
        }
    }

}
