package com.example.attendance;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;

public class Common extends Application {
//	to install shortcut icon
//	https://stackoverflow.com/questions/6337431/android-create-shortcuts-on-the-home-screen

	private static final String LOG_TAG = "attendance_logs";
	private static final String SHARED_PREF_NAME = "SHARED_PREF_ATTENDANCE";
	private static android.content.SharedPreferences _sharedPref;
	private static HashMap<Context, Toast> toastHistory = new HashMap<>();
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"dd-MMM-yy", Locale.getDefault()
	);
	private static SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(
			"HH:mm:ss", Locale.getDefault()
	);

	@Override
	public void onCreate() {
		super.onCreate();
		_sharedPref = this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
	}

	public static void ConsoleLog(String msg) {
		Log.i(LOG_TAG, msg == null ? "null" : msg);
	}

	public static String FormatString(String format, Object... args) {
		return String.format(Locale.getDefault(), format, args);
	}

	public static void CenteredToast(Context activityContext, String text, int duration) {
		Toast toast = Toast.makeText(activityContext, text, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static String FormatDate(java.util.Date date) {
		return simpleDateFormat.format(date);
	}

	public static String FormatTime(java.util.Date date) {
		return simpleTimeFormat.format(date);
	}

	static class SharedPreferences {

		static void setItem(String key, String value) {
			_sharedPref.edit().putString(key, value).apply();
		}

		static String getItem(String key, String defValue) {
			return _sharedPref.getString(key, defValue);
		}
	}

	public static void ShowToast(Context context, String text) {
		Toast toast = toastHistory.get(context);
		if (toast != null && toast.getView() != null && !toast.getView().isShown()) {
//      Toast.makeText();
		}
//		https://stackoverflow.com/questions/9002706/how-to-get-a-text-from-a-toast-object
	}


	public static class Date {
		private java.util.Date date;

		public Date(@Nullable java.util.Date date) {
			if (date == null) {
				date = new java.util.Date();
			}
			this.date = date;
		}

		public long totalTime() {
			return new java.util.Date().getTime() - this.date.getTime();
		}

		public float totalSeconds() {
			return this.totalTime() / 1000;
		}

		public float totalMinutes() {
			return this.totalSeconds() / 60;
		}

		public float totalHours() {
			return this.totalMinutes() / 60;
		}

		public float totalDays() {
			return this.totalHours() / 24;
		}
	}
}
