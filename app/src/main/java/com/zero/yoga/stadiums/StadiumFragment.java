package com.zero.yoga.stadiums;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zero.yoga.R;
import com.zero.yoga.base.BaseFragment;

/**
 * Created by zero on 2018/8/13.
 */

public class StadiumFragment extends BaseFragment {

    private ImageView ivBanner;


    public static StadiumFragment newInstance() {
//        Bundle args = new Bundle();
        StadiumFragment fragment = new StadiumFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    @android.support.annotation.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_stadium, container, false);
        initFragment(StadiumListFragment.newInstance(false), R.id.flContainer);

        return root;
    }

}
