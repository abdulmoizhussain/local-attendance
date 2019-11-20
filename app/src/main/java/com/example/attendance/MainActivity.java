package com.example.attendance;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    checkAndAskLocationPermission();

    // Do not directly do any initializations or call of methods here, before taking permissions.
    // Do everything inside the function: continueToCallOnCreateMethods();
    // after requesting for permissions.
  }

  private void checkAndAskLocationPermission() {
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
        || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
      // if location permission is denied !
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
      return;
    }
    continueToCallOnCreateMethods();
  }

  private void continueToCallOnCreateMethods() {
    settleBottomNavigationBar();
    initViewModel();
  }

  private void settleBottomNavigationBar() {
    BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_status, R.id.navigation_logs)
        .build();
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    NavigationUI.setupWithNavController(navView, navController);

    resetSizeOfNavigationHost(navView);
  }

  private void initViewModel() {

//		attendanceViewModel = ViewModelProviders.of(this).get(StatusFragmentViewModel.class);
//		// manages on its own that activity is visible or not, if not it wont do anything.
//		attendanceViewModel.allAttendances.observe(this, attendanceEntities -> {
//			mAttendanceEntityList.clear();
//			if (attendanceEntities != null) {
//				mAttendanceEntityList.addAll(attendanceEntities);
//			}

//				if (mAttendanceAdapter == null) {
//					mAttendanceAdapter = new AttendanceAdapter(MainActivity.this, mAttendanceEntityList);
//					mRecyclerView.setAdapter(mAttendanceAdapter);
//				}
//				mAttendanceAdapter.notifyDataSetChange();
//		});
  }

  /*
   So that bottom of parent-fragment can end at the top of bottom-tab-navigation-bar.
  */
  private void resetSizeOfNavigationHost(BottomNavigationView bottomNavigationView) {
    DisplayMetrics navMetrics = bottomNavigationView.getResources().getDisplayMetrics();

    View parentFragment = findViewById(R.id.nav_host_fragment);
    DisplayMetrics fragmentMetrics = parentFragment.getResources().getDisplayMetrics();

    parentFragment.getLayoutParams().width = fragmentMetrics.widthPixels - navMetrics.widthPixels;
    parentFragment.getLayoutParams().height = fragmentMetrics.heightPixels - navMetrics.heightPixels;
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    for (int i = 0; i < permissions.length; i++) {
      if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
        Toast.makeText(this, "Please provide Location Permission !", Toast.LENGTH_SHORT).show();
        finish();
        return;
      }
    }
    continueToCallOnCreateMethods();
  }
}
