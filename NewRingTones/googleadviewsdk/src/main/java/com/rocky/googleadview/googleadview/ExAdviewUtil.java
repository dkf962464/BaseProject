package com.rocky.googleadview.googleadview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.rocky.googleadview.applicationview.FixedThread;
import com.rocky.googleadview.callinterface.BaseTotalController;
import com.rocky.googleadview.callinterface.ExInterface;
import com.rocky.googleadview.callinterface.FixedThreadInterface;

import java.util.Random;

/**
 * GOOGLE激励广告
 */
public class ExAdviewUtil {
    private static RewardedAd rewardedAd;
    private static RewardedAdLoadCallback adLoadCallback;
    private static ExInterface controllers;
    private static String[]ids;
    public static void loadExAdView(final Context context, final ExInterface controller ,String ... id) {
        controllers = controller;
       ids=id;
        rewardedAd = new RewardedAd(context, id[0]);
        // Ad successfully loaded.
        // Ad failed to load.
        adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
                LogUtils.e("google adview load success");
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
                controller.onLoadFailedAdView(errorCode);
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);

    }

    public static void showExView(Context context) {
        if (null!=controllers){
            if (rewardedAd.isLoaded()) {
                adviewCallBack(context);
                LogUtils.d("Perform if method");
            } else {
                if (null != adLoadCallback)
                    rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
            }
        }else{
            LogUtils.e("Please load the ad first");
        }

    }

    /**
     *
     * @param context   onRewardedAdFailedToShow() ERROR For more details, please visit
     *
     *  ERROR_CODE_INTERNAL_ERROR - 内部出现问题。
     * ERROR_CODE_AD_REUSED - 激励广告已展示。 RewardedAd 对象是一次性对象，且仅可展示一次。将新的 RewardedAd 实例化并进行加载，即可展示新的广告。
     * ERROR_CODE_NOT_READY - 广告尚未成功加载。
     * ERROR_CODE_APP_NOT_FOREGROUND - 应用未在前台运行时，广告无法展示。
     */
    private static void adviewCallBack(final Context context) {
        RewardedAdCallback adCallback = new RewardedAdCallback() {
            public void onRewardedAdOpened() {
                // Ad opened.
                controllers.onRewardedAdOpened();
            }

            public void onRewardedAdClosed() {
                // Ad closed.
                rewardedAd=createAndLoadRewardedAd(context);
                controllers.onRewardedAdClosed();
            }

            @Override
            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                super.onUserEarnedReward(rewardItem);
                //参数为这个激励广告的奖额信息
                int amount = rewardItem.getAmount();
                String type = rewardItem.getType();
                controllers.onUserEarnedReward(amount,type);
                LogUtils.e("adview  show complete");
            }

            public void onRewardedAdFailedToShow(int errorCode) {
                // Ad failed to display
                controllers.onRewardedAdFailedToShow(errorCode);
            }
        };
        rewardedAd.show((Activity) context, adCallback);
    }
    private static RewardedAd createAndLoadRewardedAd(Context context) {
        String id="ca-app-pub-3940256099942544/5224354917";
        Random random=new Random();
        LogUtils.e("id.size"+ids.length);
        if (null!=ids&&ids.length!=0){
            id=ids[random.nextInt(ids.length)];
            LogUtils.e(id);
        }

        RewardedAd rewardedAd = new RewardedAd(context,id);

        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
        return rewardedAd;
    }
}
