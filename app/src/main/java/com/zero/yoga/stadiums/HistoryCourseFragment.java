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
import com.zero.yoga.adapter.HistoryCourseAdapter;
import com.zero.yoga.adapter.StadiumListAdapter;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.base.BaseFragment;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.bean.response.HistoryCourseResponse;
import com.zero.yoga.bean.response.MerchanListResponse;
import com.zero.yoga.bean.response.MyCourseResponse;
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

public class HistoryCourseFragment extends BaseFragment {

    private static final String TAG = "MyCourse";

    private XRecyclerView xrecyclerView;

    private static int PERPAGE = 20;

    private int mCurrIndex = 0;

    private HistoryCourseAdapter historyCourseAdapter;

    public static HistoryCourseFragment newInstance() {
//        Bundle args = new Bundle();
        HistoryCourseFragment fragment = new HistoryCourseFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    @android.support.annotation.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_stadium_historycourse, container, false);
        xrecyclerView = root.findViewById(R.id.xrecycler_view);
        historyCourseAdapter = new HistoryCourseAdapter(getActivity());
        xrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xrecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        xrecyclerView.setAdapter(historyCourseAdapter);

        xrecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mCurrIndex = 0;
                fetchData();
            }

            @Override
            public void onLoadMore() {
                mCurrIndex = (int) Math.ceil((historyCourseAdapter.getItemCount() / PERPAGE));
                fetchData();
            }
        });
        mCurrIndex = 0;
        fetchData();
        return root;
    }

    public void fetchData() {
        fillData(PERPAGE, mCurrIndex);
    }

    private void fillData(final int perpage, final int nowindex) {

        HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).userCourseHisCourse(nowindex, perpage)
                .compose(new RxHelper<HistoryCourseResponse>().io_main((BaseActivity) getActivity(), true))
                .subscribe(new RxObserver<HistoryCourseResponse>() {
                    @Override
                    public void _onNext(HistoryCourseResponse response) {
                        if (xrecyclerView == null || getActivity() == null || response == null || response.getData() == null || historyCourseAdapter == null) {
                            return;
                        }
                        if (mCurrIndex == 0) {
                            historyCourseAdapter.setDataList(response.getData().getRows());
                            xrecyclerView.refreshComplete();
                        } else {
                            historyCourseAdapter.addDataList(response.getData().getRows());
                            xrecyclerView.loadMoreComplete();
                        }
//                        if (response.getData().getTotal() < PERPAGE) {
//                            xrecyclerView.setNoMore(true);
//                        } else {
//                            xrecyclerView.setNoMore(false);
//                        }
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
}
