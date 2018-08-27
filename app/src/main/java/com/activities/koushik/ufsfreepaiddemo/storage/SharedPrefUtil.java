package com.activities.koushik.ufsfreepaiddemo.storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mindtree on 8/23/2018.
 */
public class SharedPrefUtil {
    private static final String SHARED_PREF = "pref_main";
    private static final String IS_PURCHASED = "is_purchased";

    public static boolean getPurchased(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF, Activity.MODE_PRIVATE);
        return preferences.getBoolean(IS_PURCHASED, false);
    }

    public static void isPurchased(Context context, boolean isPurchased) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_PURCHASED, isPurchased);
        editor.apply();
    }
}
