package com.rocky.googleadview.applicationview;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;
import com.rocky.googleadview.callinterface.BaseService;

public class AdviewApplication extends Application implements BaseService {
    @Override
    public void initial(@NonNull Context context, @NonNull String uid) {
        Log.e("initial", "success" );
        MobileAds.initialize(context, uid);

    }
}
