package com.rocky.googleadview.googleadview;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.rocky.googleadview.applicationview.FixedThread;
import com.rocky.googleadview.callinterface.BaseTotalController;
import com.rocky.googleadview.callinterface.FixedThreadInterface;

import java.util.concurrent.ExecutorService;

/**
 * GOOGLE插屏广告
 */
public class InAdviewUtil {
    private static InterstitialAd mInterstitialAd;
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void showInAdView(@NonNull final Context context, @NonNull String adUnitid, final BaseTotalController controller) {
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(adUnitid);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                controller.closeAdView();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                controller.onLoadFailedAdView(i,mInterstitialAd);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });
    }
}
