package com.example.attendance;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		settleBottomNavigationBar();
		
		initViewModel();
	}
	
	private void settleBottomNavigationBar() {
		BottomNavigationView navView = findViewById(R.id.nav_view);
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
	
	private void initViewModel() {

//		attendanceViewModel = ViewModelProviders.of(this).get(AttendanceViewModel.class);
//		// manages on its own that activity is visible or not, if not it wont do anything.
//		attendanceViewModel.mAttendances.observe(this, attendanceEntities -> {
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
}
