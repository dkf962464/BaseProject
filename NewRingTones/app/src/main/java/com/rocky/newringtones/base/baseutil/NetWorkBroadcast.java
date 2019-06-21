package com.rocky.newringtones.base.baseutil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;

public class NetWorkBroadcast extends BroadcastReceiver  {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            assert connectivityManager != null;
            Network activeNetwork = connectivityManager.getActiveNetwork();
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (null==networkCapabilities){
                BaseActivity.netWorkState(NetWorkManager.hasNetWorkState(NetWorkEnum.NO_NETWORK));
            }else{
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                    BaseActivity.netWorkState(NetWorkManager.hasNetWorkState(NetWorkEnum.WIFI));
                } else  {
                    BaseActivity.netWorkState(NetWorkManager.hasNetWorkState(NetWorkEnum.MOBEL));
                }
            }
        }
    }
}
