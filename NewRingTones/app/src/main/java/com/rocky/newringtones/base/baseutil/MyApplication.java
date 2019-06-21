package com.rocky.newringtones.base.baseutil;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.rocky.googleadview.applicationview.AdviewApplication;
public class MyApplication extends AdviewApplication {
    private static MyApplication instance;
    private NetWorkBroadcast mReceiver;

    public static MyApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initial(this,"ca-app-pub-8519724823288511~9170676886");
        MyApplication.instance=this;
        register();
//        FountUtil.setDefaultFont(this,"SERIF", "fonts/sss.ttf");
    }



    private void register() {
        mReceiver = new NetWorkBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, filter);//注册
    }
}
