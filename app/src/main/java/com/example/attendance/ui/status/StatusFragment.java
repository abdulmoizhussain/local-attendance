package com.example.attendance.ui.status;

import android.Manifest;
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

import com.example.attendance.AttendanceEntity;
import com.example.attendance.AttendanceViewModel;
import com.example.attendance.Common;
import com.example.attendance.R;
import com.example.attendance.ui.logs.LogsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import androidx.core.content.ContextCompat;

public class StatusFragment extends Fragment {

  private StatusViewModel statusViewModel;
  private LogsViewModel logsViewModel;
  private AttendanceViewModel attendanceViewModel;
  private List<AttendanceEntity> attendanceEntityList = new ArrayList<>();
  private Button checkInButton, checkOutButton;

  public View onCreateView(
      @NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {

    attendanceViewModel = ViewModelProviders.of(this).get(AttendanceViewModel.class);

    logsViewModel = ViewModelProviders.of(this).get(LogsViewModel.class);
    logsViewModel.attendanceEntities.observe(this, (List<AttendanceEntity> attendanceEntities) -> {
      attendanceEntityList.clear();
      if (attendanceEntities != null)
        attendanceEntityList.addAll(attendanceEntities);
    });

    statusViewModel = ViewModelProviders.of(this).get(StatusViewModel.class);

    View root = inflater.inflate(R.layout.fragment_status, container, false);
    final TextView textView = root.findViewById(R.id.text_status);
    statusViewModel.getText().observe(this, textView::setText);

    checkInButton = root.findViewById(R.id.button_check_in);
    checkInButton.setOnClickListener(checkInButtonOnClickListener);

    checkOutButton = root.findViewById(R.id.button_check_out);
    checkOutButton.setOnClickListener(checkOutButtonOnClickListener);

    resetCheckInAndCheckOutButtonStates();
    return root;
  }

  private View.OnClickListener checkInButtonOnClickListener = (View view) -> {
    Location currentLocation = getCurrentLocation();
    if (currentLocation == null) {
      return;
    }

    Future<AttendanceEntity> entityFutureWhereCheckOutLocationIsNull =
        attendanceViewModel.getOneWhereCheckOutLocationIsNull();

    try {
      AttendanceEntity attendanceEntity =
          entityFutureWhereCheckOutLocationIsNull.get(10, TimeUnit.SECONDS);

      Common.ConsoleLog(attendanceEntity.getCheckInTime().toString());
      Common.ConsoleLog(attendanceEntity.getCheckOutTime().toString());

    } catch (ExecutionException | InterruptedException | TimeoutException e) {
      e.printStackTrace();
    }
  };

  private View.OnClickListener checkOutButtonOnClickListener = (View view) -> {

  };

  private Location getCurrentLocation() {
    LocationManager locationManager = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);

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
    if (!isProviderEnabled) {
      // When provider is disabled.
      Common.CenteredToast(getActivity(), "Please turn on Location Service.", Toast.LENGTH_LONG);
      startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
      return null;
    }

    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    if (location == null) {
      Common.CenteredToast(getActivity(), "Unable to get location !", Toast.LENGTH_LONG);
      return null;
    }

    return location;
  }

  private void resetCheckInAndCheckOutButtonStates() {
    Future<AttendanceEntity> entityFutureWhereCheckOutLocationIsNull =
        attendanceViewModel.getOneWhereCheckOutLocationIsNull();

    try {
      AttendanceEntity entity = entityFutureWhereCheckOutLocationIsNull.get(10, TimeUnit.SECONDS);

      if (entity == null) {
        // No entity found with null check-out, user will do check-in only.
        checkInButton.setEnabled(true);
        checkOutButton.setEnabled(false);
      } else {
        // Found entity with null check-out, user will do check-out only.
        checkInButton.setEnabled(false);
        checkOutButton.setEnabled(true);
      }

    } catch (ExecutionException | InterruptedException | TimeoutException e) {
      e.printStackTrace();
    }
  }
}