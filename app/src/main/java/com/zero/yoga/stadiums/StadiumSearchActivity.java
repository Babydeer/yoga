package com.zero.yoga.stadiums;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.zero.yoga.MainActivity;
import com.zero.yoga.R;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.utils.BackUtils;
import com.zero.yoga.utils.ToastUtils;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by zero on 2018/8/13.
 */

public class StadiumSearchActivity extends BaseActivity {

    private static final String TAG = "StadiumSearch";


    private ImageView ivBack;
    private EditText svStadium;
    private ImageView ivSearch;

    private StadiumListFragment stadiumListFragment;


    public static void jump2StadiumSearchActivity(final Context context) {
        Intent intent = new Intent(context, StadiumSearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stadiumsearch);
        ivBack = findViewById(R.id.ivBack);
        svStadium = findViewById(R.id.svStadium);
        BackUtils.onBackPress(this, ivBack);
        ivSearch = findViewById(R.id.ivSearch);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String key = svStadium.getText().toString().trim();
                if (TextUtils.isEmpty(key)) {
                    ToastUtils.showShortToast("关键字不能为空...");
                    return;
                }
                doSearch(key);
            }
        });

        if (stadiumListFragment == null) {
            stadiumListFragment = StadiumListFragment.newInstance(true);
            initFragment(stadiumListFragment, R.id.flContainer);
        }

    }


    private void doSearch(final String key) {
        if (stadiumListFragment != null) {
            stadiumListFragment.doSearch(key);
        }
    }
}
