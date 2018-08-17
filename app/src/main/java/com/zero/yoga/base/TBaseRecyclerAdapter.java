package com.zero.yoga.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zero on 2016-07-28.
 */
public abstract class TBaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "Adapter";

    protected ArrayList<T> mDatas = new ArrayList<T>();
    protected Context mContext;

    protected int mSelectPostion = 0;

    public TBaseRecyclerAdapter(Context context) {
        mContext = context;
    }

    public int getSelectPostion() {
        return mSelectPostion;
    }

    public void setSelectPostion(int selectPostion) {
        this.mSelectPostion = selectPostion;
        notifyDataSetChanged();
    }

    public void setDataList(List<T> datas) {
        if (datas != null) {
            mDatas.clear();
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void addDataList(List<T> datas) {
        if (datas != null) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void addData(T data) {
        if (data != null) {
            mDatas.add(data);
        }
        notifyDataSetChanged();
    }

    public void removeData(T data) {
        if (data != null) {
            mDatas.remove(data);
        }
        notifyDataSetChanged();
    }

    public T getData(int position) {
        return mDatas.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        BindData(holder, getData(position), position);
    }

    public abstract RecyclerView.ViewHolder CreateViewHolder(ViewGroup parent, int viewType);

    public abstract void BindData(final RecyclerView.ViewHolder holder, final T data, final int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


}
