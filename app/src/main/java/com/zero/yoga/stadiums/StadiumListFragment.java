package com.zero.yoga.stadiums;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.zero.yoga.R;
import com.zero.yoga.adapter.StadiumListAdapter;
import com.zero.yoga.adapter.TestAdapter;
import com.zero.yoga.base.BaseFragment;
import com.zero.yoga.base.BaseLazyFragment;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.utils.ItemClickSupport;
import com.zero.yoga.view.DividerItemDecoration;
import com.zero.yoga.view.TestFragment;

import java.util.ArrayList;


/**
 * Created by zero on 2018/8/13.
 */

public class StadiumListFragment extends BaseLazyFragment {

    private static final String TAG = "StadiumList";

    private XRecyclerView xrecyclerView;

    private TBaseRecyclerAdapter testAdapter;
    ArrayList<String> data;

    public static StadiumListFragment newInstance() {
//        Bundle args = new Bundle();
        StadiumListFragment fragment = new StadiumListFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    @android.support.annotation.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_stadiumlist, container, false);

        xrecyclerView = root.findViewById(R.id.xrecycler_view);
        testAdapter = new StadiumListAdapter(getActivity());
        xrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xrecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        xrecyclerView.setAdapter(testAdapter);

        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("test: " + i);
        }

        testAdapter.setDataList(data);

        xrecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                data.clear();
                for (int i = 0; i < 10; i++) {
                    data.add("test: " + i);
                }
                testAdapter.setDataList(data);
            }

            @Override
            public void onLoadMore() {
                for (int i = data.size(); i < 10; i++) {
                    data.add("test: " + i);
                }
                testAdapter.addDataList(data);
            }
        });

        ItemClickSupport.addTo(xrecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int
                    position, View v) {
                Logger.t(TAG).i("position: " + position);
                StadiumDetailsActivity.jump2StadiumDetailsActivity
                        (getActivity());
            }
        });

        return root;
    }

    @Override
    public void fetchData() {
        Logger.t(TAG).d("fetchData");

    }
}
