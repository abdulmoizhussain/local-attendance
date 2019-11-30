package com.example.attendance;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

	private final int REQUEST_CHECK_LOCATION_SETTINGS = 1;

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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);

		switch (requestCode) {
			case REQUEST_CHECK_LOCATION_SETTINGS:
				switch (resultCode) {
					case Activity.RESULT_OK:
						// All required changes were successfully made
//                     ...
						break;
					case Activity.RESULT_CANCELED:
						// The user was asked to change settings, but chose not to
//                     ...
						break;
					default:
						break;
				}
				break;
		}
	}

	private void testing() {
		//https://www.google.com/search?rlz=1C1CHBF_enPK873PK873&sxsrf=ACYBGNQe6Vuf89bYGyifMD0F8w7XThaQHg%3A1574356692564&ei=1MbWXZ-EIonDgQacmpiYDw&q=android+location+no+thatnks&oq=android+location+no+thatnks&gs_l=psy-ab.3..33i160l2.446912.451936..452033...1.2..0.253.5798.0j5j22......0....1..gws-wiz.......0i71j35i39j0i67j0j0i131j0i273j0i20i263j0i22i30j0i13j0i13i30j33i22i29i30.ERCHc-OB2Cg&ved=0ahUKEwifvqPb5_vlAhWJYcAKHRwNBvMQ4dUDCAs&uact=5
		//https://developer.android.com/training/location/change-location-settings.html
		//https://www.google.com/search?rlz=1C1CHBF_enPK873PK873&sxsrf=ACYBGNQuNRf7xoJsF341F95ZHM9ACwsztg%3A1574357913100&ei=mcvWXeXnBcjrgAaVjYPAAg&q=android+import+LocationSettingsRequest&oq=android+import+LocationSettingsRequest&gs_l=psy-ab.3..0i8i30j0i333l4.3779.4841..5645...0.2..0.219.437.2-2......0....1..gws-wiz.......0i71j0i8i7i30.rKMjiCvALNc&ved=0ahUKEwilmKOh7PvlAhXINcAKHZXGACgQ4dUDCAs&uact=5
		//https://github.com/codepath/android_guides/wiki/Retrieving-Location-with-LocationServices-API

		GoogleApiClient.ConnectionCallbacks connectionCallbacks =
				new GoogleApiClient.ConnectionCallbacks() {
					@Override
					public void onConnected(@Nullable Bundle bundle) {
					}

					@Override
					public void onConnectionSuspended(int i) {
					}
				};

		GoogleApiClient.OnConnectionFailedListener connectionFailedListener =
				(ConnectionResult connectionResult) -> {
				};

		GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this)
				.addApi(LocationServices.API)
				.addConnectionCallbacks(connectionCallbacks)
				.addOnConnectionFailedListener(connectionFailedListener)
				.build();
		googleApiClient.connect();

		LocationRequest locationRequest = new LocationRequest();
		locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		locationRequest.setInterval(30 * 1000);
		locationRequest.setFastestInterval(5 * 1000);

		LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
				.addLocationRequest(locationRequest);

		//**************************
		builder.setAlwaysShow(true); // this is the key ingredient
		//**************************


		Task<LocationSettingsResponse> locationSettingsResponseTask = LocationServices
				.getSettingsClient(this)
				.checkLocationSettings(builder.build());

		locationSettingsResponseTask.addOnSuccessListener(
				this,
				(LocationSettingsResponse locationSettingsResponse) -> {
					Toast.makeText(MainActivity.this, "addOnSuccessListener", Toast.LENGTH_SHORT).show();
					// All location settings are satisfied. The client can initialize
					// location requests here.
					// ...
				}
		);

		locationSettingsResponseTask.addOnFailureListener(
				this,
				(Exception e) -> {
					Toast.makeText(MainActivity.this, "addOnFailureListener", Toast.LENGTH_SHORT).show();
					if (e instanceof ResolvableApiException) {
						// Location settings are not satisfied, but this can be fixed
						// by showing the user a dialog.
						try {
							// Show the dialog by calling startResolutionForResult(),
							// and check the result in onActivityResult().
							ResolvableApiException resolvable = (ResolvableApiException) e;
							resolvable.startResolutionForResult(
									MainActivity.this,
									REQUEST_CHECK_LOCATION_SETTINGS
							);
						} catch (IntentSender.SendIntentException sendEx) {
							// Ignore the error.
						}
					}
				}
		);

		locationSettingsResponseTask.addOnCompleteListener((Task<LocationSettingsResponse> task) -> {
			try {
				LocationSettingsResponse response = task.getResult(ApiException.class);
				// All location settings are satisfied. The client can initialize location
				// requests here.
				//   ...
			} catch (ApiException exception) {
				switch (exception.getStatusCode()) {
					case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
						// Location settings are not satisfied. But could be fixed by showing the
						// user a dialog.
						try {
							// Cast to a resolvable exception.
							ResolvableApiException resolvable = (ResolvableApiException) exception;
							// Show the dialog by calling startResolutionForResult(),
							// and check the result in onActivityResult().
							resolvable.startResolutionForResult(
									MainActivity.this,
									REQUEST_CHECK_LOCATION_SETTINGS
							);
						} catch (IntentSender.SendIntentException e) {
							// Ignore the error.
						} catch (ClassCastException e) {
							// Ignore, should be an impossible error.
						}
						break;
					case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
						// Location settings are not satisfied. However, we have no way to fix the
						// settings so we won't show the dialog.
//                     ...
						break;
				}
			}
		});

//			PendingResult<LocationSettingsResult> result =
//					LocationServices.SettingsApi.checkLocationSettings(
//							googleApiClient,
//							builder.build()
//					);
//			result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
//				@Override
//				public void onResult(LocationSettingsResult result) {
//					final Status status = result.getStatus();
//					final LocationSettingsStates state = result.getLocationSettingsStates();
//					switch (status.getStatusCode()) {
//						case LocationSettingsStatusCodes.SUCCESS:
//							// All location settings are satisfied. The client can initialize location
//							// requests here.
//							break;
//						case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//							// Location settings are not satisfied. But could be fixed by showing the user
//							// a dialog.
//							try {
//								// Show the dialog by calling startResolutionForResult(),
//								// and check the result in onActivityResult().
//								status.startResolutionForResult(
//										MainActivity.this,
//										1000
//								);
//							} catch (IntentSender.SendIntentException e) {
//								// Ignore the error.
//							}
//							break;
//						case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//							// Location settings are not satisfied. However, we have no way to fix the
//							// settings so we won't show the dialog.
//							break;
//					}
//				}
//			});
	}
}
