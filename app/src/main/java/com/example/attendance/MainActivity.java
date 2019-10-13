package com.example.attendance;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.attendance.ui.AttendanceDao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BottomNavigationView navView = findViewById(R.id.nav_view);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
				R.id.navigation_status, R.id.navigation_logs)
				.build();
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
		NavigationUI.setupWithNavController(navView, navController);
		
		// So that bottom of parent-fragment can end at the top of bottom-tab-navigation-bar.
		DisplayMetrics navMetrics = navView.getResources().getDisplayMetrics();
		
		View parentFragment = findViewById(R.id.nav_host_fragment);
		DisplayMetrics fragmentMetrics = parentFragment.getResources().getDisplayMetrics();
		parentFragment.getLayoutParams().width = fragmentMetrics.widthPixels - navMetrics.widthPixels;
		parentFragment.getLayoutParams().height = fragmentMetrics.heightPixels - navMetrics.heightPixels;
		
		
//		AttendanceDao asdf = AttendanceDao();
//		asdf.getAll();
	}
	
	private List<> mAttendanceList;
	private List
}
