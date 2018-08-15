package com.zero.yoga.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zero.yoga.R;
import com.zero.yoga.base.BaseLazyFragment;
import com.zero.yoga.stadiums.StadiumFragment;
import com.zero.yoga.stadiums.StadiumListFragment;

import org.jetbrains.annotations.Nullable;

/**
 * Created by zero on 2018/8/13.
 */

public class MineFragment extends BaseLazyFragment {


    public static MineFragment newInstance() {
//        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
//        fragment.setArguments(args);
        return fragment;
    }


    @android.support.annotation.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, @android.support.annotation.Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mine, container, false);
        return root;
    }

    @Override
    public void fetchData() {

    }
}
