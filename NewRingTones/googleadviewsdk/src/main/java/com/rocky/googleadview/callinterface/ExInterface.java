package com.rocky.googleadview.callinterface;


import com.google.android.gms.ads.rewarded.RewardedAd;

public interface ExInterface {
    void onLoadFailedAdView(int errorCode);
    void onRewardedAdOpened();
    void onRewardedAdClosed();
    void onRewardedAdFailedToShow(int errorCode);
    void onUserEarnedReward(int amount,String type);
}
