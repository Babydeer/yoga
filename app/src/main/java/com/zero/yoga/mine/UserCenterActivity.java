package com.zero.yoga.mine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
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
import com.zero.yoga.bean.response.CourseDelResponse;
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
import com.zero.yoga.utils.DialogUtils;
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
    private TextView tvSex;
    private TextView tvPhoneNo;

    private Button btnLogout;

    private ArrayList<String> imagePaths = new ArrayList<String>();

    private String photoPath = "";

    private boolean isUpdate = false;

    private int grader = 0;//默认为男

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                final ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                if (TextUtils.isEmpty(photoPath) || !TextUtils.equals(photoPath, photos.get(0))) {
                    isUpdate = true;
                }
                photoPath = photos.get(0);
                Config.UserInfo.setPhotoPath(photoPath);
                Logger.t(TAG).i(photoPath);
                Uri uri = Uri.fromFile(new File(photoPath));
                Glide.with(UserCenterActivity.this).load(uri).placeholder(R.drawable.personal_ic_pic)
                        .crossFade()
                        .transform(new GlideCircleTransform(UserCenterActivity.this))
                        .into(ivPortrait);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        final String nickName = etName.getText().toString().trim();
        if (!TextUtils.equals(nickName, Config.UserInfo.getNickname())
                || isUpdate
                || Config.UserInfo.getGrade() != grader) {
            Intent intent = new Intent(UserCenterActivity.this, UpdateInfoService.class);
            intent.putExtra("nickName", nickName);
            intent.putExtra("grader", grader);
            startService(intent);
        }

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
        tvSex = findViewById(R.id.tvSex);
        tvPhoneNo = findViewById(R.id.tvPhoneNo);

        btnLogout = findViewById(R.id.btnLogout);

        tvTitle.setText("个人中心");

        ivPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectMul();
            }
        });

        initData();

        tvSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] sexs = {"男", "女"};
                DialogUtils.showListDialog(UserCenterActivity.this, sexs, "请选择性别", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {//男
                            grader = 0;
                            tvSex.setText("男");

                        } else {
                            grader = 1;
                            tvSex.setText("女");
                        }
                    }
                });
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取消弹框提示
                DialogUtils.showNormalDialog(UserCenterActivity.this, R.mipmap.yogachain_ic, "Yogo", "确定要退出吗?", "确定", "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        doLogout();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
            }
        });

    }

    private void initData() {
        if (!TextUtils.isEmpty(Config.UserInfo.getPhotoPath())) {
            Uri uri = Uri.fromFile(new File(Config.UserInfo.getPhotoPath()));
            Glide.with(UserCenterActivity.this).load(uri).placeholder(R.drawable.personal_ic_pic)
                    .crossFade()
                    .transform(new GlideCircleTransform(UserCenterActivity.this))
                    .into(ivPortrait);
        } else {
            Glide.with(UserCenterActivity.this).load(Config.UserInfo.getHeaderPicture()).placeholder(R.drawable.personal_ic_pic)
                    .crossFade()
                    .transform(new GlideCircleTransform(UserCenterActivity.this))
                    .into(ivPortrait);
        }

        Logger.t(TAG).d("nickname: "+ Config.UserInfo.getNickname());
        etName.setText(Config.UserInfo.getNickname());
        tvPhoneNo.setText(Config.UserInfo.getPhoneNo());
        tvSex.setText(Config.UserInfo.getGrade() == 0 ? "男" : "女");

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
