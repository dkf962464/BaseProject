package com.rocky.newringtones.home.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rocky.googleadview.callinterface.ExInterface;
import com.rocky.googleadview.googleadview.ExAdviewUtil;
import com.rocky.googleadview.googleadview.LogUtils;
import com.rocky.newringtones.base.baseutil.JsonResult;
import com.rocky.newringtones.base.baseutil.OkHttpManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

import static com.rocky.newringtones.base.baseutil.BaseActivity.isJson;

public  class HomeModelImpl implements HomeModelInterface {

    @Override
    public void doLogin(HomeCallBackInter inter) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "wanggaolei");
        map.put("pwd", "123456");
        OkHttpManager.getInstances().post("https://xxhdpi.utools.club/WeCharCost/login", map, new OkHttpManager.OnCallback() {
            @Override
            public void onError(IOException e) {
                Log.e("eror", "" + e.getMessage());
                inter.resultFailed(e.getMessage());
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
                        inter.resultSuccess(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void requestExView(Context context) {
        ExAdviewUtil.loadExAdView(context, new ExInterface() {
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
    }
}
