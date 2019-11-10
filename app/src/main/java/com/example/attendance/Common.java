package com.example.attendance;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.Locale;

public class Common extends Application {
  private static final String LOG_TAG = "attendance_logs";
  private static final String SHARED_PREF_NAME = "SHARED_PREF_ATTENDANCE";
  private static android.content.SharedPreferences _sharedPref;

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

  static class SharedPreferences {

    static void setItem(String key, String value) {
      _sharedPref.edit().putString(key, value).apply();
    }

    static String getItem(String key, String defValue) {
      return _sharedPref.getString(key, defValue);
    }
  }
}
