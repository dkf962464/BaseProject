package com.rocky.newringtones.home.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.rocky.googleadview.googleadview.ExAdviewUtil;
import com.rocky.googleadview.googleadview.LogUtils;
import com.rocky.newringtones.R;
import com.rocky.newringtones.base.baseutil.BaseActivity;
import com.rocky.newringtones.home.model.LoginJsonBean;
import com.rocky.newringtones.home.presenter.HomePresenter;
import com.rocky.vipmicrogame.JniEncryption;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomePresenter,HomeActivity> implements  HomeViewInterface {
    static {
        System.loadLibrary("test");
    }
    @BindView(R.id.play_but)
    ImageView mImageView;
    private long mExitTime;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.login();
        presenter.requestExView(this);
    }
    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }
        @Override
    protected void makeData(){
        String encrypt = JniEncryption.encrypt("xxhdpi");
        String decrypt = JniEncryption.decrypt(encrypt);
        Log.e("tags", "" + encrypt);
        Log.e("tags", "" + decrypt);

        mImageView.setOnClickListener(v -> {
            if ((System.currentTimeMillis() - mExitTime) > 4000) {
                ExAdviewUtil.showExView(HomeActivity.this);
                mExitTime = System.currentTimeMillis();
            } else {
                toastColor(Color.YELLOW, "你的手速太快了,别点了", 1.0f);
            }
        });
    }
    //是否开启沉浸式
    @Override
    public boolean isImmersive() {
        return true;
    }
    //返回子类Presenter
    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public void resultSuccess(Object object) {
        LoginJsonBean loginJsonBean= (LoginJsonBean) object;
        Toast.makeText(this, ""+loginJsonBean.toString(), Toast.LENGTH_SHORT).show();
        LogUtils.e(loginJsonBean.getName());
    }

    @Override
    public void resultFailed(String msg) {
        LogUtils.e(msg);
    }
}
