package com.zero.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zero.yoga.R;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by zero on 2018/8/14.
 */

public class TestAdapter extends TBaseRecyclerAdapter<String> {


    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder CreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder holder = null;
        View view = inflater.inflate(R.layout.item_test, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void BindData(RecyclerView.ViewHolder holder, String data, int position) {
        final ViewHolder vholder = (ViewHolder) holder;

        vholder.tvTest.setText(data);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTest;

        ViewHolder(View view) {
            super(view);
            AutoUtils.auto(view);
            tvTest = view.findViewById(R.id.tvTest);
        }
    }
}
