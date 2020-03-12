package com.bach.dv.basemvp.network.cached;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bach.dv.basemvp.App;

public class APISharedPrefsUtils {

    public static final String KEY_SHARED_PREF = "project_r_2_api";

    private APISharedPrefsUtils() {

    }

    private static SharedPreferences preferences;

    static SharedPreferences getInstance() {
        if (preferences == null) {
            preferences = App.getAppContext().getSharedPreferences(KEY_SHARED_PREF, Context.MODE_PRIVATE);
        }

        return preferences;
    }

    /**
     * Helper method to retrieve a String value from {@link SharedPreferences}.
     *
     * @param key
     * @return The value from shared preferences, or null if the value could not be read.
     */
    public static String getStringPreference(String key) {
        String value = null;
        SharedPreferences preferences = getInstance();
        if (preferences != null) {
            value = preferences.getString(key, null);
        }
        return value;
    }

    /**
     * Helper method to write a String value to {@link SharedPreferences}.
     *
     * @param key
     * @param value
     * @return true if the new value was successfully written to persistent storage.
     */
    public static boolean setStringPreference(String key, String value) {
        SharedPreferences preferences = getInstance();
        if (preferences != null && !TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            return editor.commit();
        }
        return false;
    }

    /**
     * Helper method to retrieve a float value from {@link SharedPreferences}.
     *
     * @param key
     * @param defaultValue A default to return if the value could not be read.
     * @return The value from shared preferences, or the provided default.
     */
    public static float getFloatPreference(String key, float defaultValue) {
        float value = defaultValue;
        SharedPreferences preferences = getInstance();
        if (preferences != null) {
            value = preferences.getFloat(key, defaultValue);
        }
        return value;
    }

    /**
     * Helper method to write a float value to {@link SharedPreferences}.
     *
     * @param key
     * @param value
     * @return true if the new value was successfully written to persistent storage.
     */
    public static boolean setFloatPreference(String key, float value) {
        SharedPreferences preferences = getInstance();
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putFloat(key, value);
            return editor.commit();
        }
        return false;
    }

    /**
     * Helper method to retrieve a long value from {@link SharedPreferences}.
     *
     * @param key
     * @param defaultValue A default to return if the value could not be read.
     * @return The value from shared preferences, or the provided default.
     */
    public static long getLongPreference(String key, long defaultValue) {
        long value = defaultValue;
        SharedPreferences preferences = getInstance();
        if (preferences != null) {
            value = preferences.getLong(key, defaultValue);
        }
        return value;
    }

    /**
     * Helper method to write a long value to {@link SharedPreferences}.
     *
     * @param key
     * @param value
     * @return true if the new value was successfully written to persistent storage.
     */
    public static boolean setLongPreference(String key, long value) {
        SharedPreferences preferences = getInstance();
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(key, value);
            return editor.commit();
        }
        return false;
    }

    /**
     * Helper method to retrieve an integer value from {@link SharedPreferences}.
     *
     * @param key
     * @param defaultValue A default to return if the value could not be read.
     * @return The value from shared preferences, or the provided default.
     */
    public static int getIntegerPreference(String key, int defaultValue) {
        int value = defaultValue;
        SharedPreferences preferences = getInstance();
        if (preferences != null) {
            value = preferences.getInt(key, defaultValue);
        }
        return value;
    }

    /**
     * Helper method to write an integer value to {@link SharedPreferences}.
     *
     * @param key
     * @param value
     * @return true if the new value was successfully written to persistent storage.
     */
    public static boolean setIntegerPreference(String key, int value) {
        SharedPreferences preferences = getInstance();
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            return editor.commit();
        }
        return false;
    }/**
     * Helper method to write an integer value to {@link SharedPreferences}.
     *
     * @param key
     * @return true if the new value was successfully written to persistent storage.
     */
    public static boolean removeKey(String key) {
        SharedPreferences preferences = getInstance();
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(key);
            return editor.commit();
        }
        return false;
    }

    /**
     * Helper method to retrieve a boolean value from {@link SharedPreferences}.
     *
     * @param key
     * @param defaultValue A default to return if the value could not be read.
     * @return The value from shared preferences, or the provided default.
     */
    public static boolean getBooleanPreference(String key, boolean defaultValue) {
        boolean value = defaultValue;
        SharedPreferences preferences = getInstance();
        if (preferences != null) {
            value = preferences.getBoolean(key, defaultValue);
        }
        return value;
    }

    /**
     * Helper method to write a boolean value to {@link SharedPreferences}.
     *
     * @param key
     * @param value
     * @return true if the new value was successfully written to persistent storage.
     */
    public static boolean setBooleanPreference(String key, boolean value) {
        SharedPreferences preferences = getInstance();
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            return editor.commit();
        }
        return false;
    }

}