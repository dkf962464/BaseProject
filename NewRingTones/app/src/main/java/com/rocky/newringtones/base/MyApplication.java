package com.rocky.newringtones.base;
import com.rocky.googleadview.applicationview.AdviewApplication;
public class MyApplication extends AdviewApplication {
    private static MyApplication instance;
    public static MyApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initial(this,"ca-app-pub-8519724823288511~9170676886");
        instance=this;
//        FountUtil.setDefaultFont(this,"SERIF", "fonts/sss.ttf");
    }
}
