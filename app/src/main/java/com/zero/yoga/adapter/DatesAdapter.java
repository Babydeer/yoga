package com.zero.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zero.yoga.R;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.stadiums.DateBean;

/**
 * Created by zero on 2018/8/14.
 */

public class DatesAdapter extends TBaseRecyclerAdapter<DateBean> {


    public DatesAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder CreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder holder = null;
        View view = inflater.inflate(R.layout.item_date, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void BindData(RecyclerView.ViewHolder holder, DateBean data, int position) {
        final ViewHolder vholder = (ViewHolder) holder;

        vholder.tvMonth.setText(data.getDateStr());
        vholder.tvWeek.setText(data.getWeekStr());

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvMonth;
        public TextView tvWeek;

        ViewHolder(View view) {
            super(view);
            tvMonth = view.findViewById(R.id.tvMonth);
            tvWeek = view.findViewById(R.id.tvWeek);
        }
    }
}
