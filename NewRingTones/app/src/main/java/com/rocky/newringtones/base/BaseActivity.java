package com.rocky.newringtones.base;
import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.rocky.newringtones.R;

import butterknife.ButterKnife;
public abstract class BaseActivity extends AppCompatActivity {
    private NetWorkBroadcast mReceiver;
    private static int firstApp;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isImmersive()) {
            StatusBarUtil.immersive(this);
        }
        setContentView(setContentView());
        ButterKnife.bind(this);
        firstApp++;
        BaseAppManager.getInstance().addActivity(this);
        if (firstApp==1){
            register();
        }
        initData();
        initView();

    }
    protected abstract int setContentView();
    protected abstract void initData();

    protected abstract void initView();

    public abstract boolean isImmersive();

    protected int colorXmlToJava(int color) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            return getColor(color);
        }
        return Color.WHITE;
    }

    private void register() {
        mReceiver = new NetWorkBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, filter);//注册
        Toast.makeText(this, "receiver register success", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        register();
    }


    public static void netWorkState(int netState) {
        int color;
        String state = null;
        switch (netState) {
            case 0:
                state=MyApplication.getInstance().getResources().getString(R.string.network_none);
                break;
            case 1:
                state=MyApplication.getInstance().getResources().getString(R.string.network_wifi);;
                break;
            case 2:
                state=MyApplication.getInstance().getResources().getString(R.string.network_mobel);;
                break;
        }
        if (firstApp!=1||netState==2||netState==0){
            if (netState==2){
                color=Color.YELLOW;
            }else{
                color=Color.RED;
            }
            toastColor(color,state,1.3f);
        }else{
            toastColor(Color.GREEN,state,1.3f);
        }
    }

    private static void toastColor(int color, String msg, @FloatRange(from = 0f,to = 2.0f) float size){
        LayoutInflater inflater = LayoutInflater
                .from(MyApplication.getInstance());
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.toast_textcolor,null);
        TextView textView = (TextView) view.findViewById(R.id.toast_id);
        SpannableString ss = new SpannableString(msg);
        ss.setSpan(new ForegroundColorSpan(color), 0, ss.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(size), 0, ss.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        Toast.makeText(MyApplication.getInstance(),textView.getText(), Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseAppManager.getInstance().finishActivity(this);
        if (null!=mReceiver)
        unregisterReceiver(mReceiver);
    }

}

