package com.rocky.newringtones.util;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by hp on 5/20/2018.
 */

public class Font {

    static String ubuntu_regular = "Font/Ubuntu-Regular.ttf";
    static String ubuntu_light = "Font/Ubuntu-Light.ttf";
    static String ubuntu_medium = "Font/Ubuntu-Medium.ttf";
    static String ubuntu_bold = "Font/Ubuntu-Bold.ttf";
    static String lemon_milk = "Font/LemonMilk.otf";
    static String neuro_political = "Font/neuro_political.ttf";
    static String prime_light = "Font/Prime Light.otf";
    static String prime_regular = "Font/Prime Regular.otf";
    static String aquatico_regular = "Font/Aquatico-Regular.otf";
    static String raleway_regular = "Font/Raleway-Regular.ttf";
    static String raleway_semi_bold = "Font/Raleway-SemiBold.ttf";
    static String raleway_light = "Font/Raleway-Light.ttf";


    public static Typeface ubuntu_regular_font(Context context) {
        return Typeface.createFromAsset(context.getResources().getAssets(), ubuntu_regular);
    }

    public static Typeface ubuntu_medium_font(Context context) {
        return Typeface.createFromAsset(context.getResources().getAssets(), ubuntu_medium);
    }

    public static Typeface ubuntu_bold_font(Context context) {
        return Typeface.createFromAsset(context.getResources().getAssets(), ubuntu_bold);
    }

    public static Typeface ubuntu_light_font(Context context) {
        return Typeface.createFromAsset(context.getResources().getAssets(), ubuntu_light);
    }

    public static Typeface lemon_milk_font(Context context) {
        return Typeface.createFromAsset(context.getResources().getAssets(), lemon_milk);
    }

    public static Typeface neuro_political_font(Context context) {
        return Typeface.createFromAsset(context.getResources().getAssets(), neuro_political);
    }

    public static Typeface prime_light_font(Context context) {
        return Typeface.createFromAsset(context.getResources().getAssets(), prime_light);
    }

    public static Typeface prime_regular_font(Context context) {
        return Typeface.createFromAsset(context.getResources().getAssets(), prime_regular);
    }

    public static Typeface aquatico_regular_font(Context context) {
        return Typeface.createFromAsset(context.getResources().getAssets(), aquatico_regular);
    }

    public static Typeface raleway_regular_font(Context context) {
        return Typeface.createFromAsset(context.getResources().getAssets(), raleway_regular);
    }

    public static Typeface raleway_light_font(Context context) {
        return Typeface.createFromAsset(context.getResources().getAssets(), raleway_light);
    }

    public static Typeface raleway_semi_bold_font(Context context) {
        return Typeface.createFromAsset(context.getResources().getAssets(), raleway_semi_bold);
    }

}
