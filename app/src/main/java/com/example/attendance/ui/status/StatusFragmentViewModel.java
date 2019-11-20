package com.example.attendance.ui.status;

import android.app.Application;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.attendance.repository.sqlite.AttendanceRepository;
import com.example.attendance.sqlite.context.AttendanceEntity;

import java.util.Date;
import java.util.concurrent.Future;

public class StatusFragmentViewModel extends AndroidViewModel {
  private AttendanceRepository mRepository;

  public StatusFragmentViewModel(@NonNull Application application) {
    super(application);
    mRepository = AttendanceRepository.getInstance(application.getApplicationContext());
  }

  Future checkInUserNow(Location currentLocation) {
    return mRepository.insertEntityWithCheckInStatus(currentLocation);
  }

  Future checkOutUserNow(Location currentLocation, AttendanceEntity attendanceEntity) {
    attendanceEntity.setCheckOutTime(new Date());
    attendanceEntity.setCheckInLatitude(currentLocation.getLatitude());
    attendanceEntity.setCheckOutLongitude(currentLocation.getLongitude());

    return mRepository.updateOne(attendanceEntity);
  }

  Future<AttendanceEntity> getOneWhereCheckOutIsNull() {
    return mRepository.getOneWhereCheckOutIsNull();
  }
}
