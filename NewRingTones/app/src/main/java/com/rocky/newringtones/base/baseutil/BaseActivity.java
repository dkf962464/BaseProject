package com.rocky.newringtones.base.baseutil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.rocky.googleadview.googleadview.LogUtils;
import com.rocky.newringtones.R;
import com.rocky.newringtones.base.basemvp.presenter.BasePresenter;
import com.rocky.newringtones.base.basemvp.view.BaseViewInter;
import com.rocky.newringtones.util.StatusBarUtil;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter, V extends BaseViewInter> extends AppCompatActivity {
    private NetWorkBroadcast mReceiver;
    protected P presenter;
    private static float sNoncompatDesity;
    private static float sNoncompatScaledDesity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (isImmersive()) {
            StatusBarUtil.immersive(this);
        }
        setContentView(setContentView());
//        setCustomDesity(this,getApplication());
        presenter = getPresenter();
        presenter.attach((V) this);
//        presenter.attach(V);
        ButterKnife.bind(this);
        BaseAppManager.getInstance().addActivity(this);
        initView();
        makeData();
        super.onCreate(savedInstanceState);
    }

    protected abstract int setContentView();

    protected abstract void initView();

    protected abstract void makeData();

    public abstract boolean isImmersive();

    protected abstract P getPresenter();

    protected int colorXmlToJava(int color) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            return getColor(color);
        }
        return Color.WHITE;
    }

    public static void netWorkState(int netState) {
        int color;
        String state = null;
        switch (netState) {
            case 0:
                state = MyApplication.getInstance().getResources().getString(R.string.network_none);
                break;
            case 1:
                state = MyApplication.getInstance().getResources().getString(R.string.network_wifi);
                break;
            case 2:
                state = MyApplication.getInstance().getResources().getString(R.string.network_mobel);
                break;
        }

        if (netState == 2) {
            color = Color.YELLOW;
        } else {
            color = Color.RED;
        }
        toastColor(color, state, 1.3f);
    }

    protected static void toastColor(int color, String msg, @FloatRange(from = 0f, to = 2.0f) float size) {
        LayoutInflater inflater = LayoutInflater
                .from(MyApplication.getInstance());
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.toast_textcolor, null);
        TextView textView = (TextView) view.findViewById(R.id.toast_id);
        SpannableString ss = new SpannableString(msg);
        ss.setSpan(new ForegroundColorSpan(color), 0, ss.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(size), 0, ss.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        Toast.makeText(MyApplication.getInstance(), textView.getText(), Toast.LENGTH_LONG).show();
    }

    protected boolean screenConfiguration() {
        Configuration mConfiguration = this.getResources().getConfiguration();
        //获取设置的配置信息
        int ori = mConfiguration.orientation;
        if (ori == Configuration.ORIENTATION_PORTRAIT) {
            return true;
            // 竖屏

        } else if (ori == Configuration.ORIENTATION_LANDSCAPE) {

            return false;
            // 横屏
        }
        return false;
    }

    public static boolean isJson(String json) {
        JsonElement jsonElement;
        try {
            jsonElement = new JsonParser().parse(json);
        } catch (Exception e) {
            return false;
        }
        if (jsonElement == null) {
            return false;
        }
        if (!jsonElement.isJsonObject()) {
            return false;
        }
        return true;
    }


    private static void setCustomDesity(@NonNull Activity activity, @NonNull final Application application) {
        final DisplayMetrics appdisplayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDesity == 0) {
            sNoncompatDesity = appdisplayMetrics.density;
            sNoncompatScaledDesity = appdisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override


                public void onConfigurationChanged(Configuration configuration) {
                    if (configuration != null && configuration.fontScale > 0) {
                        sNoncompatScaledDesity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }
        final float targetDesity = (float) appdisplayMetrics.widthPixels / 420;//375 设计图的宽度dp
        final float targetScaleDesity = targetDesity * (sNoncompatScaledDesity / sNoncompatDesity);
        final int targetDesityDpi = (int) (160 * targetDesity);

        appdisplayMetrics.density = targetDesity;
        appdisplayMetrics.scaledDensity = targetScaleDesity;
        appdisplayMetrics.densityDpi = targetDesityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDesity;
        activityDisplayMetrics.scaledDensity = targetScaleDesity;
        activityDisplayMetrics.densityDpi = targetDesityDpi;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != presenter) {
            presenter.deAttach();
        }
        LogUtils.e(BaseAppManager.getInstance().currentActivity() + "destory");
        BaseAppManager.getInstance().finishActivity(this);
        if (null != mReceiver)
            unregisterReceiver(mReceiver);
    }

    protected static boolean isEmpty(String string) {
        return TextUtils.isEmpty(string);
    }

    protected static boolean isNull(final Object obj) {
        return null == obj;
    }
}

