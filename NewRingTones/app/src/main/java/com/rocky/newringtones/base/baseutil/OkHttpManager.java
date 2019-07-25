package com.rocky.newringtones.base.baseutil;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/31.
 */

public class OkHttpManager {

    private static final String TAG = "OkHttpUtils";
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private static HashMap<String, String> params;
    private static OkHttpManager mOkHttpUtils;
    public interface OnCallback {

        void onError(IOException e);

        void onSuccess(Response response);
    }

    private final OkHttpClient client;

    public OkHttpManager() {
        // 初始化Okhttp
        client = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).build();


    }
    public static OkHttpManager getInstances(){
        if (null==mOkHttpUtils){
            mOkHttpUtils=new OkHttpManager();
        }
        return  mOkHttpUtils;
    }

    public  void get(String url, Map<String, String> params, final OnCallback onCallback) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(url);
        // 构建参数
        if (params != null && params.size() > 0) {
            buffer.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                try {
                    buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "utf-8"));
                    buffer.append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            // 删除最后一个 &
            buffer.deleteCharAt(buffer.length() - 1);
        }

        // 构建请求对象
        Request request = new Request.Builder()
                .get()
                .url(buffer.toString())
                .build();

        // 发起异步请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                // 将子线程任务运行到主线程
                mHandler.post(() -> onCallback.onError(e));
            }

            @Override
            public void onResponse(Call call, final Response response) {

//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        onCallback.onSuccess(response);
//                    }
//                });
                onCallback.onSuccess(response);
            }
        });
    }


    public void post(String url, Map<String, String> params, final OnCallback onCallback) {
        // 构建 builder 对象  主要用于添加参数
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                try {
                    builder.add(entry.getKey(), URLEncoder.encode(entry.getValue(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        // 构建 body 对象
        FormBody body = builder.build();
        // 构建请求对象
        Request request = new Request.Builder().post(body).url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(() -> onCallback.onError(e));
            }

            @Override
            public void onResponse(Call call, final Response response)  {

                mHandler.post(() -> onCallback.onSuccess(response));
            }
        });
    }
}
