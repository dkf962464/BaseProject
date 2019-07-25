package com.rocky.newringtones.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rocky.newringtones.R;

import java.io.File;
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
    private static ArrayList<Object> objectArrayList = new ArrayList<>();
    private static String[] bannerId = new String[]{"ca-app-pub-8519724823288511/4175792460", "ca-app-pub-8519724823288511/8637781656", "ca-app-pub-8519724823288511/8280836235", "ca-app-pub-8519724823288511/4615664216"
            , "ca-app-pub-8519724823288511/8827631140"
    };

    private static String[] inAdviewId = new String[]{"ca-app-pub-8519724823288511/7999487405", "ca-app-pub-8519724823288511/1376091508", "ca-app-pub-8519724823288511/9476220603", "ca-app-pub-8519724823288511/8570333040"
            , "ca-app-pub-8519724823288511/9500271338"
    };

    private static String[] reAdviewId = new String[]{"ca-app-pub-8519724823288511/2659530814", "ca-app-pub-8519724823288511/2467959128", "ca-app-pub-8519724823288511/7298502028", "ca-app-pub-8519724823288511/5832489061"
            , "ca-app-pub-8519724823288511/8322921037"};
    private static Random random = new Random();

//    public static WallpaperObject repleaceHttpsToHttp(WallpaperObject wallpaperObject) {
//        for (int i = 0; i < wallpaperObject.getWallpaperList().size(); i++) {
//            String medium = wallpaperObject.getWallpaperList().get(i).getMedium();
//            String portraitUrl = wallpaperObject.getWallpaperList().get(i).getPortraitUrl();
//            wallpaperObject.getWallpaperList().get(i).setMedium(medium.replace("https", "http"));
//            wallpaperObject.getWallpaperList().get(i).setPortraitUrl(portraitUrl.replace("https", "http"));
//        }
//        return wallpaperObject;
//    }

//    public static ArrayList<Object> repleaceHttpsToHttp(ArrayList<Object> list) {
//        for (int i = 0; i < list.size(); i++) {
//            String tiny = ((WallpaperObject) list.get(i)).getTiny();
//            String medium = ((WallpaperObject) list.get(i)).getMedium();
//            String portraiturl = ((WallpaperObject) list.get(i)).getPortraitUrl();
//            ((WallpaperObject) list.get(i)).setTiny(tiny.replace("https", "http"));
//            ((WallpaperObject) list.get(i)).setMedium(medium.replace("https", "http"));
//            ((WallpaperObject) list.get(i)).setPortraitUrl(portraiturl.replace("https", "http"));
//        }
//        return list;
//    }

//    public static void startIntent(Context context, ArrayList<Object> objectArrayList, int position) {
//        Intent intent = new Intent(context.getApplicationContext(), WallpaperViewer.class);
//        WallpaperObject wallpaperObjec = (WallpaperObject) objectArrayList.get(position);
//        intent.putExtra("imgurl", wallpaperObjec.getPortraitUrl());
//        intent.putExtra("imgid", wallpaperObjec.getId());
////        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//    }

//    // 改变tablayout下划线长度
//    public static void reflex(final TabLayout tabLayout) {
//        tabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
////                    int dp10 = Utility.dpToPx(10);
//                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
//                        View tabView = mTabStrip.getChildAt(i);
//                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
//                        mTextViewField.setAccessible(true);
//                        TextView mTextView = (TextView) mTextViewField.get(tabView);
//                        tabView.setPadding(0, 0, 0, 0);
//                        int width = 0;
//                        width = mTextView.getWidth();
//                        if (width == 0) {
//                            mTextView.measure(0, 0);
//                            width = mTextView.getMeasuredWidth();
//                        }
//                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
//                        params.width = dp10;
//                        params.leftMargin = dp10;
//                        params.rightMargin = dp10;
//                        tabView.setLayoutParams(params);
//                        tabView.invalidate();
//                    }
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    //    public static void adViewSwitchLocalPosition(Context context,int index,LinearLayout linearLayout){
//       if (index%4==0){
//           linearLayout.setVisibility(View.VISIBLE);
//           AdView adView = AdViewUtil.getHAdView(context, context.getResources().getString(R.string.admob_banner_id), AdSize.BANNER, 0);
//           linearLayout.addView(adView);
//       }
//    }
    //list 去重顺序改变
    public static List<String> removeDuplicate(List<String> list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    //list 去重保持原有顺序
    public static <T> List removeDuplicateWithOrder(List<T> list) {
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

    public static String firstUpCase(String string) {
        if (null != string) {
            int i = string.length();
            return string.substring(0, 1).toUpperCase() + string.substring(1, i).toLowerCase();
        }
        return "";
    }

//    public static void randomBackground(Context context, TextView textView) {
//        Random random = null;
//        if (null == random) {
//            random = new Random();
//        }
//        int nextInt = random.nextInt(6);
//
//        switch (nextInt) {
//            case 0:
//                textView.setBackgroundResource(R.drawable.dog_shape);
//                textView.setTextColor(context.getResources().getColor(R.color.greenStart));
//                break;
//            case 1:
//                textView.setBackgroundResource(R.drawable.cars_sharpe);
//                textView.setTextColor(context.getResources().getColor(R.color.selectcolor));
//                break;
//            case 2:
//                textView.setBackgroundResource(R.drawable.nature_sharpe);
//                textView.setTextColor(context.getResources().getColor(R.color.blueEnd));
//                break;
//            case 3:
//                textView.setBackgroundResource(R.drawable.girl_shape);
//                textView.setTextColor(context.getResources().getColor(R.color.girl));
//                break;
//            case 4:
//                textView.setBackgroundResource(R.drawable.land_sharpe);
//                textView.setTextColor(context.getResources().getColor(R.color.purpleStart));
//                break;
//            case 5:
//                textView.setBackgroundResource(R.drawable.love_shape);
//                textView.setTextColor(context.getResources().getColor(R.color.blueStart));
//                break;
//
//        }
//    }

    //自动弹出键盘
    public static void showInputMethod(Context context) {
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //强制隐藏Android输入法窗口
        // inputManager.hideSoftInputFromWindow(edit.getWindowToken(),0);
    }



    public static boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

//    public static void addUserInfomation(final Context context) {
//        HashMap<String, String> params = new HashMap<>();
//        String urlParams = getURLParams(USER_SERVER_NAME, context);
//        Log.e("tag", "addUser: " + urlParams);
//        OkHttpUtils.getInstances().get(_URL_BASE + urlParams, params, new OkHttpUtils.OnCallback() {
//            @Override
//            public void onError(IOException e) {
//                Log.e("errorMsg", "onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onSuccess(Response response) {
//                try {
//                    String json = response.body().string();
//                    AddUserResult addUserResponse = new Gson().fromJson(json, AddUserResult.class);
//                    if (addUserResponse != null && addUserResponse.getCode().equals("200")) {
//                        String result = JieMaTools.getInstance().decrypt(addUserResponse.getResult());
//                        AddUserBean userStatusBean = new Gson().fromJson(result, AddUserBean.class);
//                        if (userStatusBean != null && userStatusBean.getIsWrite().equals("1")) {
//                            FacebookSdk.setApplicationId("363116647682952");
//                            FacebookSdk.sdkInitialize(context);
//                            AppEventsLogger.activateApp(context, "363116647682952");
//                            Log.e("TAGss", "" + addUserResponse.getResult());
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    private static String getSerial(Context context) {
//        String serial = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//                serial = Build.getSerial();
//            }
//        } else {
//            serial = Build.SERIAL;
//        }
//        Log.e("isSerial", "getSerial: " + serial);
//
//        return serial;
//    }


//    public static String getURLParams(String serverName, Context context) {
//        StringBuilder stringBuilder = new StringBuilder("");
////        Build.SERIAL
//        String param = String.format("uId=%s&op=%s&deviceId=%s&v=%s",
//                Constant.Credentials.APP_ID,
//                AppUtil.getSimOperator(context),
//                getSerial(context),
//                AppUtil.getAppVersionCode(context));
//        String sign = BaseMD5.MD5(param + "&c=Lj78&60Ke97%53");
//        stringBuilder.append("sn=");
//        stringBuilder.append(serverName);
//        stringBuilder.append("&" + param);
//        stringBuilder.append("&sign=");
//        stringBuilder.append(sign);
//        stringBuilder.append("&netType=");
//        stringBuilder.append(String.valueOf(AppUtil.getNetworkType(context)));
//        stringBuilder.append("&eventType=");
//        stringBuilder.append("0");
////        return "ehunae=" + JieMaTools.getInstance().encrypt(stringBuilder.toString()) + "&v=" + DevInfoUtil.getAppVersionCode(context);
//
//        return "sdata=" + JieMaTools.getInstance().encrypt(stringBuilder.toString()) + "&v=" + AppUtil.getAppVersionCode(context);
//    }

    public static String getAdmobelId() {
        if (null == random) {
            random = new Random();
        }
        int nextInt = random.nextInt(bannerId.length);
        return bannerId[nextInt];
//        return "ca-app-pub-3940256099942544/6300978111";
    }

    public static String getInAdmobeId() {
        if (null == random) {
            random = new Random();
        }
        int nextInt = random.nextInt(inAdviewId.length);
        return inAdviewId[nextInt];
//        return "ca-app-pub-3940256099942544/1033173712";
    }

    public static String getReAdviewId() {
        if (null == random) {
            random = new Random();
        }
        int nextInt = random.nextInt(reAdviewId.length);
        return reAdviewId[nextInt];
//        return "ca-app-pub-3940256099942544/5224354917";
    }


    public static void toSelfSetting(Context context) {

        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            mIntent.setAction(Intent.ACTION_VIEW);
            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            mIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(mIntent);
    }

    public static boolean isMIUI() {
        String manufacturer = Build.MANUFACTURER;
        if ("xiaomi".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    public static boolean isEMUI() {
        String manufacturer = Build.MANUFACTURER;
        if ("HUAWEI".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    public static boolean isOPPO() {
        String manufacturer = Build.MANUFACTURER;
        if ("OPPO".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    public static boolean isVIVO() {
        String manufacturer = Build.MANUFACTURER;
        if ("vivo".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

//    public static void showDialogTipUserRequestPermission(final Activity ac, final MiuiOppo miuiOppo) {
//
//        new AlertDialog.Builder(ac)
//                .setTitle("Storage permissions are not available")
//                .setMessage("In the -Application Settings - Permissions - Allow App to use storage permissions to save user data")
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        miuiOppo.click();
//                    }
//                })
//                .setNegativeButton("Cancal", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        miuiOppo.cancle();
//                    }
//                }).setCancelable(false).show();
//    }

    //    public static void intent2SetWallPaper(Context context, String path) {
//        Uri uriPath = getUriWithPath(path);
//        Intent intent;
//
//        // 针对EMUI
//        if (isEMUI()) {
//            try {
//                ComponentName componentName = new ComponentName("com.android.gallery3d", "com.android.gallery3d.app.Wallpaper");
//                intent = new Intent(Intent.ACTION_VIEW);
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                intent.setDataAndType(uriPath, "image/*");
//                intent.putExtra("mimeType", "image/*");
//                intent.setComponent(componentName);
//                context.startActivity(intent);
//            } catch (Exception e) {
//                e.printStackTrace();
//                try {
//                    WallpaperManager.getInstance(context.getApplicationContext()).setBitmap(ImageUtil.getImageBitmap(path));
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//
//            // 针对MIUI
//        } else if (isMIUI()) {
//            try {
//                ComponentName componentName = new ComponentName("com.android.thememanager", "com.android.thememanager.activity.WallpaperDetailActivity");
//                intent = new Intent("miui.intent.action.START_WALLPAPER_DETAIL");
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                intent.setDataAndType(uriPath, "image/*");
//                intent.putExtra("mimeType", "image/*");
//                intent.setComponent(componentName);
//                context.startActivity(intent);
//            } catch (Exception e) {
//
//            }
//
//            // 其他
//        } else {
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
////                context.startActivity(WallpaperManager.getInstance(context.getApplicationContext())
////                        .getCropAndSetWallpaperIntent(getUriWithPath(path)));
////            } else {
////                try {
////                    WallpaperManager.getInstance(context.getApplicationContext()).setBitmap(ImageUtil.getImageBitmap(path));
////                } catch (IOException e1) {
////                    e1.printStackTrace();
////                }
////            }
    //适配小米手机设置壁纸
    public static void settingMiuiWallpaper(Context context, Uri uri) {
        try {
            Intent intent;
            ComponentName componentName = new ComponentName("com.android.thememanager", "com.android.thememanager.activity.WallpaperDetailActivity");
            intent = new Intent("miui.intent.action.START_WALLPAPER_DETAIL");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("mimeType", "image/*");
            intent.setComponent(componentName);
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e("errorMsgWallpapper", "" + e.getMessage());
        }
    }
}

