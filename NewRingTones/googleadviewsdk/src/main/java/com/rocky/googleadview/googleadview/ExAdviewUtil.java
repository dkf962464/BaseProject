package com.rocky.googleadview.googleadview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.rocky.googleadview.applicationview.FixedThread;
import com.rocky.googleadview.callinterface.BaseTotalController;
import com.rocky.googleadview.callinterface.FixedThreadInterface;

/**
 * GOOGLE激励广告
 */
public class ExAdviewUtil {
    private static RewardedAd rewardedAd;

    public static void showExAdView(final Context context, String id, final BaseTotalController controller) {
        rewardedAd = new RewardedAd(context, id);
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
                controller.onLoadFailedAdView(errorCode);
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
        if (rewardedAd.isLoaded()) {
            adviewCallBack(context, controller);
            LogUtils.d("Perform if method");
        } else {
            FixedThread.getThread(new FixedThreadInterface() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        adviewCallBack(context, controller);
                        LogUtils.d("Perform thread method");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static void adviewCallBack(Context context, final BaseTotalController controller) {
        RewardedAdCallback adCallback = new RewardedAdCallback() {
            public void onRewardedAdOpened() {
                // Ad opened.
            }

            public void onRewardedAdClosed() {
                // Ad closed.
                controller.closeAdView();
            }

            public void onUserEarnedReward(@NonNull RewardItem reward) {
                // User earned reward.
            }

            public void onRewardedAdFailedToShow(int errorCode) {
                // Ad failed to display
                controller.onLoadFailedAdView(errorCode);
            }
        };
        rewardedAd.show((Activity) context, adCallback);
    }
}
