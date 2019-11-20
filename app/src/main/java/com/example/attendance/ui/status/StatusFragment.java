package com.example.attendance.ui.status;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.attendance.sqlite.context.AttendanceEntity;
import com.example.attendance.Common;
import com.example.attendance.R;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import androidx.core.content.ContextCompat;

public class StatusFragment extends Fragment {

  private AlertDialog alertDialogLoading;
  private StatusFragmentViewModel statusFragmentViewModel;
  private Button checkInButton, checkOutButton;
  private TextView textViewCheckInStatus;

  public View onCreateView(
      @NonNull LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {

    alertDialogLoading = new AlertDialog.Builder(getContext()).create();
    alertDialogLoading.setCancelable(false);
    alertDialogLoading.setMessage(getString(R.string.title_loading));

    statusFragmentViewModel = ViewModelProviders.of(this).get(StatusFragmentViewModel.class);

    View root = inflater.inflate(R.layout.fragment_status, container, false);

    checkInButton = root.findViewById(R.id.buttonCheckIn);
    checkInButton.setOnClickListener(checkInButtonOnClickListener);

    checkOutButton = root.findViewById(R.id.buttonCheckOut);
    checkOutButton.setOnClickListener(checkOutButtonOnClickListener);

    textViewCheckInStatus = root.findViewById(R.id.textViewCheckInStatus);

    resetCheckInAndCheckOutButtonStates();
    return root;
  }

  private View.OnClickListener checkInButtonOnClickListener = (View view) -> {
    Location currentLocation = getCurrentLocation();
    if (currentLocation == null) {
      return;
    }

    try {
      alertDialogLoading.show();
      Future result = statusFragmentViewModel.checkInUserNow(currentLocation);
      result.get(10, TimeUnit.SECONDS);
    } catch (ExecutionException | InterruptedException | TimeoutException e) {
      e.printStackTrace();
      Toast.makeText(getContext(),
          "Cannot check-in at the moment plz try again.", Toast.LENGTH_SHORT).show();
    } finally {
      alertDialogLoading.dismiss();
    }
    resetCheckInAndCheckOutButtonStates();
  };

  private View.OnClickListener checkOutButtonOnClickListener = (View view) -> {
    Location currentLocation = getCurrentLocation();
    if (currentLocation == null) {
      return;
    }

    try {
      alertDialogLoading.show();
      AttendanceEntity attendanceEntityWithNullCheckOut =
          statusFragmentViewModel.getOneWhereCheckOutIsNull()
              .get(10, TimeUnit.SECONDS);

      statusFragmentViewModel
          .checkOutUserNow(currentLocation, attendanceEntityWithNullCheckOut)
          .get(10, TimeUnit.SECONDS);

      resetCheckInAndCheckOutButtonStates();
    } catch (ExecutionException | InterruptedException | TimeoutException e) {
      e.printStackTrace();
      Toast.makeText(getContext(),
          "Cannot at the moment plz try again.", Toast.LENGTH_SHORT).show();
    } finally {
      alertDialogLoading.dismiss();
    }
  };

  private Location getCurrentLocation() {
    LocationManager locationManager = (LocationManager)Objects.requireNonNull(getActivity())
            .getSystemService(Context.LOCATION_SERVICE);

    if (locationManager == null) {
      return null;
    }

    {
      int finePermissionResult = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
      int coarsePermissionResult = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);
      if (finePermissionResult != PackageManager.PERMISSION_GRANTED
          && coarsePermissionResult != PackageManager.PERMISSION_GRANTED) {
        // When location permission is denied !
        return null;
      }
    }

    boolean isProviderEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//    GPS_PROVIDER or NETWORK_PROVIDER. can use anyone.

    if (!isProviderEnabled) {
      // When provider is disabled.
      Common.CenteredToast(getActivity(), "Please turn on Location Service.", Toast.LENGTH_LONG);
      startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
      return null;
    }

    Location locationFromNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    Location locationFromGps = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    if (locationFromNetwork != null) {
      return locationFromNetwork;
    } else if (locationFromGps != null) {
      return locationFromGps;
    } else {
      Common.CenteredToast(getActivity(), "Unable to get location !", Toast.LENGTH_LONG);
      return null;
    }
//    https://developerlife.com/2010/10/20/gps/
//    Criteria c = new Criteria();
//    c.setAccuracy(Criteria.ACCURACY_FINE);
//    c.setAltitudeRequired(false);
//    c.setBearingRequired(false);
//    c.setSpeedRequired(false);
//    c.setCostAllowed(true);
//    c.setPowerRequirement(Criteria.POWER_HIGH);
  }

  private void resetCheckInAndCheckOutButtonStates() {
    alertDialogLoading.show();
    AttendanceEntity entity = null;
    try {
      entity = statusFragmentViewModel
          .getOneWhereCheckOutIsNull()
          .get(10, TimeUnit.SECONDS);
    } catch (ExecutionException | InterruptedException | TimeoutException e) {
      e.printStackTrace();
      Toast.makeText(getContext(),
          "Error loading status, plz try again.", Toast.LENGTH_SHORT).show();
    } finally {
      alertDialogLoading.dismiss();
    }

    if (entity == null) {
      // No entity found with null check-out, user will do check-in only.
      checkInButton.setEnabled(true);
      checkOutButton.setEnabled(false);
      textViewCheckInStatus.setText(null);

    } else {
      // Found entity with null check-out, user will do check-out only.
      checkInButton.setEnabled(false);
      checkOutButton.setEnabled(true);

      Common.Date checkInTime = new Common.Date(entity.getCheckInTime());

      textViewCheckInStatus.setText(null);
      textViewCheckInStatus.append("Total hours since last check-in: ");
      textViewCheckInStatus.append(Float.toString(checkInTime.totalHours()));
    }
  }
}
