package com.zero.yoga.stadiums;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.zero.yoga.R;
import com.zero.yoga.adapter.MyCourseAdapter;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.base.BaseFragment;
import com.zero.yoga.bean.response.MyCourseResponse;
import com.zero.yoga.internet.HttpUtils;
import com.zero.yoga.internet.RxHelper;
import com.zero.yoga.internet.RxObserver;
import com.zero.yoga.internet.YogaAPI;
import com.zero.yoga.utils.ToastUtils;
import com.zero.yoga.view.DividerItemDecoration;


/**
 * Created by zero on 2018/8/13.
 */

public class MyCourseFragment extends BaseFragment {

    private static final String TAG = "MyCourse";

    private XRecyclerView xrecyclerView;

    private static int PERPAGE = 20;

    private int mCurrIndex = 0;

    private MyCourseAdapter myCourseAdapter;

    public static MyCourseFragment newInstance() {
//        Bundle args = new Bundle();
        MyCourseFragment fragment = new MyCourseFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    @android.support.annotation.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_stadium_mycourse, container, false);
        xrecyclerView = root.findViewById(R.id.xrecycler_view);
        myCourseAdapter = new MyCourseAdapter(getActivity());
        xrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xrecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        xrecyclerView.setAdapter(myCourseAdapter);

        xrecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mCurrIndex = 0;
                fetchData();
            }

            @Override
            public void onLoadMore() {
                mCurrIndex = (int) Math.ceil((myCourseAdapter.getItemCount() / PERPAGE));
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

        HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).userCourseMyCourse(nowindex, perpage)
                .compose(new RxHelper<MyCourseResponse>().io_main((BaseActivity) getActivity(), true))
                .subscribe(new RxObserver<MyCourseResponse>() {
                    @Override
                    public void _onNext(MyCourseResponse response) {
                        if (xrecyclerView == null || getActivity() == null || response == null || response.getData() == null || myCourseAdapter == null) {
                            return;
                        }
                        if (mCurrIndex == 0) {
                            myCourseAdapter.setDataList(response.getData().getRows());
                            xrecyclerView.refreshComplete();
                        } else {
                            myCourseAdapter.addDataList(response.getData().getRows());
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
