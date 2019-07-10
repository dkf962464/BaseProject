package com.rocky.newringtones.util;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.rocky.lockscreen.ActivityUtil.WallpaperViewer;
import com.rocky.lockscreen.ObjectUtil.WallpaperObject;
import com.rocky.lockscreen.R;
import com.rocky.lockscreen.Utility.Utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * create by 2019/6/26
 * <p>
 * author: wgl
 * <p>
 * Believe in yourself, you can do it.
 */
public class BaseUtil {
    public static WallpaperObject repleaceHttpsToHttp(WallpaperObject wallpaperObject) {
        for (int i = 0; i < wallpaperObject.getWallpaperList().size(); i++) {
            String medium = wallpaperObject.getWallpaperList().get(i).getMedium();
            String portraitUrl = wallpaperObject.getWallpaperList().get(i).getPortraitUrl();
            wallpaperObject.getWallpaperList().get(i).setMedium(medium.replace("https", "http"));
            wallpaperObject.getWallpaperList().get(i).setPortraitUrl(portraitUrl.replace("https", "http"));
        }
        return wallpaperObject;
    }

    public static ArrayList<Object> repleaceHttpsToHttp(ArrayList<Object> list) {
        for (int i = 0; i < list.size(); i++) {
            String tiny = ((WallpaperObject) list.get(i)).getTiny();
            String medium = ((WallpaperObject) list.get(i)).getMedium();
            String portraiturl = ((WallpaperObject) list.get(i)).getPortraitUrl();
            ((WallpaperObject) list.get(i)).setTiny(tiny.replace("https", "http"));
            ((WallpaperObject) list.get(i)).setMedium(medium.replace("https", "http"));
            ((WallpaperObject) list.get(i)).setPortraitUrl(portraiturl.replace("https", "http"));
        }
        return list;
    }

    public static void startIntent(Context context, ArrayList<Object> objectArrayList, int position) {
        Intent intent = new Intent(context.getApplicationContext(), WallpaperViewer.class);
        WallpaperObject wallpaperObjec = (WallpaperObject) objectArrayList.get(position);
        intent.putExtra("imgurl", wallpaperObjec.getPortraitUrl());
        intent.putExtra("imgid", wallpaperObjec.getId());
        context.startActivity(intent);
    }

    // 改变tablayout下划线长度
    public static void reflex(final TabLayout tabLayout) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = Utility.dpToPx(10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = dp10;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void adViewSwitchLocalPosition(Context context, int index, LinearLayout linearLayout){
       if (index%4==0){
           linearLayout.setVisibility(View.VISIBLE);
           AdView adView = AdViewUtil.getHAdView(context, context.getResources().getString(R.string.admob_banner_id), AdSize.BANNER, 0);
           linearLayout.addView(adView);
       }
    }
    //list 去重顺序改变
    public static List<String> removeDuplicate(List<String> list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
    //list 去重保持原有顺序
    public static List<String> removeDuplicateWithOrder(List<String> list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        return list;
    }
    public static String firstUpCase(String string){
        if (null!=string){
            int i=string.length();
            return string.substring(0,1).toUpperCase()+string.substring(1,i).toLowerCase();
        }
        return "";
    }

    public static void randomBackground(Context context, TextView textView) {
        Random random = null;
        if (null == random) {
            random = new Random();
        }
        int nextInt = random.nextInt(6);

        switch (nextInt) {
            case 0:
                textView.setBackgroundResource(R.drawable.dog_shape);
                textView.setTextColor(context.getResources().getColor(R.color.greenStart));
                break;
            case 1:
                textView.setBackgroundResource(R.drawable.cars_sharpe);
                textView.setTextColor(context.getResources().getColor(R.color.selectcolor));
                break;
            case 2:
                textView.setBackgroundResource(R.drawable.nature_sharpe);
                textView.setTextColor(context.getResources().getColor(R.color.blueEnd));
                break;
            case 3:
                textView.setBackgroundResource(R.drawable.girl_shape);
                textView.setTextColor(context.getResources().getColor(R.color.girl));
                break;
            case 4:
                textView.setBackgroundResource(R.drawable.land_sharpe);
                textView.setTextColor(context.getResources().getColor(R.color.purpleStart));
                break;
            case 5:
                textView.setBackgroundResource(R.drawable.love_shape);
                textView.setTextColor(context.getResources().getColor(R.color.blueStart));
                break;

        }
    }
    //自动弹出键盘
    public static void showInputMethod(Context context) {
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //强制隐藏Android输入法窗口
        // inputManager.hideSoftInputFromWindow(edit.getWindowToken(),0);
    }
}
