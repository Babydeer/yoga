package com.zero.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zero.yoga.R;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.bean.response.MerchanListResponse;
import com.zero.yoga.stadiums.MerchanModel;

/**
 * Created by zero on 2018/8/14.
 */

public class StadiumListAdapter extends TBaseRecyclerAdapter<MerchanModel> {


    public StadiumListAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder CreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder holder = null;
        View view = inflater.inflate(R.layout.item_stadiumlist, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void BindData(RecyclerView.ViewHolder holder, MerchanModel data, int position) {
        final ViewHolder vholder = (ViewHolder) holder;
        Glide.with(mContext).load(data.getMerchantPictureURL()).placeholder(R.drawable.list_pic)
                .crossFade()
                .into(vholder.ivIcon);
        vholder.tvStadiumName.setText(data.getMerchantName());
        vholder.tvStadiumAddr.setText(data.getAddress());
        vholder.tvStadiumDis.setText(data.getDistanceStr());
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivIcon;
        public TextView tvStadiumName;
        public TextView tvStadiumAddr;
        public TextView tvStadiumDis;

        ViewHolder(View view) {
            super(view);
            ivIcon = view.findViewById(R.id.ivIcon);
            tvStadiumName = view.findViewById(R.id.tvStadiumName);
            tvStadiumAddr = view.findViewById(R.id.tvStadiumAddr);
            tvStadiumDis = view.findViewById(R.id.tvStadiumDis);
        }
    }
}
