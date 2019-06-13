package com.rocky.googleadview.googleadview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.rocky.googleadview.callinterface.BaSize;

/**
 * GOOGLE横幅广告
 */
public class BaAdviewUtil {
    private static BaAdviewUtil singleton;

    public static BaAdviewUtil getInstance() {
        if (singleton == null) {
            synchronized (BaAdviewUtil.class) {
                if (singleton == null) {
                    singleton = new BaAdviewUtil();
                }
            }
        }
        return singleton;
    }

    //获取横幅广告

    /**
     * Can not pass Adsize  , default Adsize.Banner
     *
     * @param context
     * @param unitId
     * @param viewGroup
     */
    public static void showBaAdView(@NonNull Context context, @NonNull String unitId, ViewGroup viewGroup,@NonNull BaSize size) {
        final AdView adView = new AdView(context);
        switch (size) {
            case BANNER:
                adView.setAdSize(AdSize.BANNER);
                break;
            case LARGE_BANNER:
                adView.setAdSize(AdSize.LARGE_BANNER);
                break;
        }
        LogUtils.e("errorCode"+size);
        adView.setAdUnitId(unitId);
        adView.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int i) {

                super.onAdFailedToLoad(i);
                LogUtils.e(i+"");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                LogUtils.e("fdas");
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        viewGroup.addView(adView);
    }
    /**
     * @param context
     * @param unitId  adview id
     * @param width   adview width
     * @param height  adview height
     * @return
     */
    @NonNull
    public static void showBaAdView(@NonNull Context context, @NonNull String unitId, int width, int height, ViewGroup group) {
        final AdView adView = new AdView(context);
        AdSize adSize = new AdSize(width, height);
        adView.setAdSize(adSize);
        adView.setAdUnitId(unitId);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                LogUtils.e("errorCode"+ i);
            }
        });
        group.addView(adView);
    }
}
