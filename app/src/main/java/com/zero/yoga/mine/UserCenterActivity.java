package com.zero.yoga.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.zero.yoga.R;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.bean.response.HistoryCourseResponse;
import com.zero.yoga.bean.response.LogoutResponse;
import com.zero.yoga.internet.HttpUtils;
import com.zero.yoga.internet.RxHelper;
import com.zero.yoga.internet.RxObserver;
import com.zero.yoga.internet.YogaAPI;
import com.zero.yoga.login.LoginActivity;
import com.zero.yoga.stadiums.DateBean;
import com.zero.yoga.stadiums.DateFactory;
import com.zero.yoga.utils.BackUtils;
import com.zero.yoga.utils.Config;
import com.zero.yoga.utils.GlideCircleTransform;
import com.zero.yoga.utils.ToastUtils;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by zero on 2018/8/13.
 */

public class UserCenterActivity extends BaseActivity {

    private static final String TAG = "UserCenter";


    private ImageView ivBack;
    private TextView tvTitle;

    private ImageView ivPortrait;

    private EditText etName;
    private EditText etSex;
    private EditText etPhoneNo;

    private Button btnLogout;

    private ArrayList<String> imagePaths = new ArrayList<String>();

    private String photoPath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                final ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                photoPath = photos.get(0);
                Logger.t(TAG).i(photoPath);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Logger.t(TAG).i(photoPath);//上传用户信息
    }

//    private void editPortrait() {
//        File file = new File(photoPath);
//        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("portrait", "portrait", requestFile);
//        RequestBody user_id = RequestBody.create(MediaType.parse("multipart/form-data"), getUserId());
//        HttpUtil.getRetrofit().create(RetrofitService.class).editPortrait(user_id, body)
//                .compose(new RxHelper<EditPortrait>().io_main(EditInformationActivity.this))
//                .subscribe(new RxSubscriber<EditPortrait>() {
//                    @Override
//                    public void _onNext(EditPortrait editPortrait) {
//                        Uri uri = Uri.fromFile(new File(photoPath));
//                        Glide.with(EditInformationActivity.this).load(uri).placeholder(R.drawable.icon_default)
//                                .crossFade()
//                                .transform(new GlideCircleTransform(EditInformationActivity.this))
//                                .into(headPortraitIv);
//                        EventBus.getDefault().post(new Event.EditPortraitEvent());
//                    }
//
//                    @Override
//                    public void _onError(String msg) {
//                        ToastUtils.show("上次头像失败");
//                        L.e(msg);
//                    }
//                });
//    }

    private void selectMul() {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(UserCenterActivity.this, PhotoPicker.REQUEST_CODE);
    }

    private void preview(int position) {
        PhotoPreview.builder()
                .setPhotos(imagePaths)
                .setCurrentItem(position)
                .start(UserCenterActivity.this);
    }


    public static void jump2UserCenterActivity(final Context context) {
        Intent intent = new Intent(context, UserCenterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_usercenter);
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);
        BackUtils.onBackPress(this, ivBack);
        ivPortrait = findViewById(R.id.ivPortrait);
        etName = findViewById(R.id.etName);
        etSex = findViewById(R.id.etSex);
        etPhoneNo = findViewById(R.id.etPhoneNo);

        btnLogout = findViewById(R.id.btnLogout);

        tvTitle.setText("个人中心");

        ivPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectMul();
            }
        });

        initData();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogout();
            }
        });

    }

    private void initData() {
        Glide.with(UserCenterActivity.this).load(Config.UserInfo.getHeaderPicture()).placeholder(R.drawable.personal_ic_pic)
                .crossFade()
                .transform(new GlideCircleTransform(UserCenterActivity.this))
                .into(ivPortrait);

        etName.setText(Config.UserInfo.getNickname());
        etPhoneNo.setText(Config.UserInfo.getPhoneNo());
        etSex.setText(Config.UserInfo.getGrade() == 0 ? "男" : "女");

    }


    private void doLogout() {
        HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).logout()
                .compose(new RxHelper<LogoutResponse>().io_main(UserCenterActivity.this, true))
                .subscribe(new RxObserver<LogoutResponse>() {
                    @Override
                    public void _onNext(LogoutResponse response) {
                        ToastUtils.showShortToast(response.getMsg());
                    }

                    @Override
                    public void _onError(String msg) {
                        Logger.t(TAG).e(msg);
                        ToastUtils.showShortToast(msg);
                    }
                });
        Config.UserInfo.clear();
        Intent intent = new Intent(UserCenterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }


}
