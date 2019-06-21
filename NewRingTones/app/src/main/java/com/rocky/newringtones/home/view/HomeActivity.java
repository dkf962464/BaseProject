package com.rocky.newringtones.home.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rocky.googleadview.callinterface.ExInterface;
import com.rocky.googleadview.googleadview.ExAdviewUtil;
import com.rocky.googleadview.googleadview.LogUtils;
import com.rocky.newringtones.R;
import com.rocky.newringtones.base.basemvp.view.BaseViewInter;
import com.rocky.newringtones.base.baseutil.BaseActivity;
import com.rocky.newringtones.base.baseutil.JsonResult;
import com.rocky.newringtones.base.baseutil.OkHttpManager;
import com.rocky.newringtones.home.model.LoginJsonBean;
import com.rocky.newringtones.home.presenter.HomePresenter;
import com.rocky.vipmicrogame.JniEncryption;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Response;

public class HomeActivity extends BaseActivity<HomePresenter,HomeActivity> implements  BaseViewInter {
    static {
        System.loadLibrary("test");
    }

    @BindView(R.id.play_but)
    ImageView mImageView;
    private long mExitTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        LinearLayout view=findViewById(R.id.view_);
//        BaAdviewUtil.showBaAdView(this,"ca-app-pub-8519724823288511/4211538839",view, BaSize.BANNER);
//        LogUtils.e("xxhdpi");
        ExAdviewUtil.loadExAdView(this, new ExInterface() {
            @Override
            public void onLoadFailedAdView(int errorCode) {
                LogUtils.e("" + errorCode);
            }

            @Override
            public void onRewardedAdOpened() {
                LogUtils.e("open adview");
            }

            @Override
            public void onUserEarnedReward(int amount, String type) {
                LogUtils.e(amount + "\t" + type);
            }

            @Override
            public void onRewardedAdClosed() {

            }

            @Override
            public void onRewardedAdFailedToShow(int errorCode) {
                LogUtils.e("" + errorCode);
            }
        }, "ca-app-pub-3940256099942544/5224354917", "ca-app-pub-3940256099942544/5224354917", "ca-app-pub-3940256099942544/5224354917", "ca-app-pub-3940256099942544/5224354917", "ca-app-pub-3940256099942544/5224354917", "ca-app-pub-3940256099942544/5224354917");
        mImageView.setOnClickListener(v -> {
            //startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 4000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                ExAdviewUtil.showExView(HomeActivity.this);
                mExitTime = System.currentTimeMillis();
            } else {
                toastColor(Color.YELLOW, "你的手速太快了,别点了", 1.0f);

                //并记录下本次点击“返回键”的时刻，以便下次进行判断

            }
        });
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {

        String encrypt = JniEncryption.encrypt("我是一个粉刷");
        String decrypt = JniEncryption.decrypt(encrypt);
        Log.e("tags", "" + encrypt);
        Log.e("tags", "" + decrypt);

    }

    @Override
    protected void initView() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "wanggaolei");
        map.put("pwd", "123456");
        OkHttpManager.getInstances().post("https://xxhdpi.utools.club/WeCharCost/login", map, new OkHttpManager.OnCallback() {
            @Override
            public void onError(IOException e) {
                Log.e("eror", "" + e.getMessage());
            }

            @Override
            public void onSuccess(Response response) {
                assert response.body() != null;
                try {
                    String json = response.body().string();
                    Gson gson = new Gson();
                    boolean isJson = isJson(json);
                    if (isJson) {
                        JsonResult<LoginJsonBean> jsonResult = gson.fromJson(json, new TypeToken<JsonResult<LoginJsonBean>>() {
                        }.getType());
                        LoginJsonBean data = jsonResult.getData();
                        Toast.makeText(HomeActivity.this, "" + data.toString(), Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean isImmersive() {
        return true;
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }

}
