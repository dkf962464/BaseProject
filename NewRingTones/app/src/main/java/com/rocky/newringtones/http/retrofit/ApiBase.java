package com.rocky.newringtones.http.retrofit;

import android.os.Environment;

import com.rocky.newringtones.http.interceptor.RetrofitInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by v.wanggaolei on 2017/8/11.
 */
public class ApiBase {
    protected final Retrofit mRetrofit;
    protected static OkHttpClient okHttpClient = null;
    /**
     * 网络请求代理对象请求
     */
    protected final ApiCommand mApiServices;
    protected ApiBase() {
        mRetrofit = new Retrofit.Builder()
                .client(getInstance().newBuilder().build())
                .baseUrl(ApiCommand.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())

//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApiServices = mRetrofit.create(ApiCommand.class);
    }
    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            //加同步安全
            synchronized (ApiBase.class) {
                if (okHttpClient == null) {
                    File sdcache = new File(Environment.getExternalStorageDirectory(), "cache");
                    int cacheSize = 10 * 1024 * 1024;
                    okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).addInterceptor(new RetrofitInterceptor())
                            .writeTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).cache(new Cache(sdcache.getAbsoluteFile(), cacheSize)).build();
                }
            }
        }
        return okHttpClient;
    }
}
