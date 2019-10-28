package com.example.attendance;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

@SuppressLint("Registered")
public class Common extends Application {
	private static final String LOG_TAG = "attendance_logs";
	private static final String SHARED_PREF_NAME = "SHARED_PREF_ATTENDANCE";
	private static SharedPreferences _sharedPref;
	
	public static void ConsoleLog(String msg) {
		Log.i(LOG_TAG, msg == null ? "null" : msg);
	}
	
	public static void setSharedPref(String key, String defValue) {
		_sharedPref.edit().putString(key, defValue).apply();
	}
	
	public static String getSharedPref(String key, String defValue) {
		return _sharedPref.getString(key, defValue);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		_sharedPref = this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
	}
}
