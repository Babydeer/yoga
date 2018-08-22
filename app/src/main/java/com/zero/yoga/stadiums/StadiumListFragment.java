package com.zero.yoga.stadiums;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.zero.yoga.R;
import com.zero.yoga.adapter.StadiumListAdapter;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.base.BaseFragment;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.bean.response.MerchanListResponse;
import com.zero.yoga.internet.HttpUtils;
import com.zero.yoga.internet.RxHelper;
import com.zero.yoga.internet.RxObserver;
import com.zero.yoga.internet.YogaAPI;
import com.zero.yoga.utils.ItemClickSupport;
import com.zero.yoga.utils.LocationUtils;
import com.zero.yoga.utils.ToastUtils;
import com.zero.yoga.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zero on 2018/8/13.
 */

public class StadiumListFragment extends BaseFragment {

    private static final String TAG = "StadiumList";

    private XRecyclerView xrecyclerView;

    private static int PERPAGE = 20;

    private int mCurrIndex = 0;

    private Location mLocation;

    private boolean isSearch = false;


    private String seachKey = "";

    private StadiumListAdapter stadiumListAdapter;


    private static class MyAsyncTask extends AsyncTask<MerchanListResponse, Void, ArrayList<MerchanModel>> {

        private Location location;
        private boolean isRefresh = false;
        private TBaseRecyclerAdapter stadiumListAdapter;

        public MyAsyncTask(TBaseRecyclerAdapter stadiumListAdapter, Location location, boolean isRefresh) {
            this.location = location;
            this.isRefresh = isRefresh;
            this.stadiumListAdapter = stadiumListAdapter;
        }

        @Override
        protected ArrayList<MerchanModel> doInBackground(MerchanListResponse[] objects) {

            MerchanListResponse response = objects[0];

            if (response == null || response.getData() == null || response.getData().getRows() == null) {
                return null;
            }

            List<MerchanListResponse.DataBean.RowsBean> rowsBeanList = response.getData().getRows();
            final int SIZE = rowsBeanList.size();

            ArrayList<MerchanModel> rets = new ArrayList<>(SIZE);
            for (int i = 0; i < SIZE; i++) {
                MerchanModel merchanModel = MerchanModel.convertMerchanModel(rowsBeanList.get(i), location);
                rets.add(merchanModel);
            }
            return rets;
        }

        @Override
        protected void onPostExecute(ArrayList<MerchanModel> merchanModels) {
            super.onPostExecute(merchanModels);
            if (isRefresh) {
                stadiumListAdapter.setDataList(merchanModels);
            } else {
                stadiumListAdapter.addDataList(merchanModels);
            }
        }
    }

    public static StadiumListFragment newInstance(boolean isSearch) {
        Bundle args = new Bundle();
        args.putBoolean("isSearch", isSearch);
        StadiumListFragment fragment = new StadiumListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @android.support.annotation.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_stadiumlist, container, false);
        isSearch = getArguments().getBoolean("isSearch", false);
        xrecyclerView = root.findViewById(R.id.xrecycler_view);
        stadiumListAdapter = new StadiumListAdapter(getActivity());
        xrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xrecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        xrecyclerView.setAdapter(stadiumListAdapter);

        xrecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mCurrIndex = 0;
                fetchData();
            }

            @Override
            public void onLoadMore() {
                mCurrIndex = (int) Math.ceil((stadiumListAdapter.getItemCount() / PERPAGE));
                fetchData();
            }
        });

        ItemClickSupport.addTo(xrecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int
                    position, View v) {
                final int index = position-1;
                if(index < 0 || index >=stadiumListAdapter.getItemCount()){
                    return;
                }
                StadiumDetailsActivity.jump2StadiumDetailsActivity
                        (getActivity(),stadiumListAdapter.getData(index));
            }
        });
        if (!isSearch) {
            fetchData();
        }
        return root;
    }

    public void fetchData() {
        fillData(seachKey, "", PERPAGE, mCurrIndex);
    }

    private void fillData(final String merchanName, final String username, final int perpage, final int nowindex) {

        mLocation = LocationUtils.getNetWorkLocation(getActivity());

        HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).merchantSelectByPage(merchanName, username, nowindex, perpage)
                .compose(new RxHelper<MerchanListResponse>().io_main((BaseActivity) getActivity(), true))
                .subscribe(new RxObserver<MerchanListResponse>() {
                    @Override
                    public void _onNext(MerchanListResponse response) {
                        if (xrecyclerView == null || getActivity() == null || response == null || response.getData() == null || stadiumListAdapter == null) {
                            return;
                        }
                        boolean isRefresh = false;
                        if (mCurrIndex == 0) {
                            isRefresh = true;
                            xrecyclerView.refreshComplete();
                        } else {
                            xrecyclerView.loadMoreComplete();
                        }
//                        if (response.getData().getTotal() < PERPAGE) {
//                            xrecyclerView.setNoMore(true);
//                        } else {
//                            xrecyclerView.setNoMore(false);
//                        }
                        new MyAsyncTask(stadiumListAdapter, mLocation, isRefresh).execute(response);
                    }

                    @Override
                    public void _onError(String msg) {
                        Logger.t(TAG).e(msg);
                        ToastUtils.showShortToast(msg);
                        xrecyclerView.refreshComplete();
                        xrecyclerView.loadMoreComplete();
                    }
                });

    }


    public void doSearch(final String key) {
        mCurrIndex = 0;
        seachKey = key;
        fillData(seachKey, "", PERPAGE, mCurrIndex);
    }
}
