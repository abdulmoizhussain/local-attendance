package com.example.attendance.repository.sqlite;

import android.content.Context;
import android.location.Location;

import androidx.lifecycle.LiveData;

import com.example.attendance.sqlite.context.AttendanceDatabase;
import com.example.attendance.sqlite.context.AttendanceEntity;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AttendanceRepository {
  private static AttendanceRepository ourInstance;
  private AttendanceDatabase mDatabase;
  private ExecutorService executorService = Executors.newSingleThreadExecutor();
  public LiveData<List<AttendanceEntity>> allAttendances;


  private AttendanceRepository(Context context) {
    mDatabase = AttendanceDatabase.getInstance(context);
    allAttendances = getAllAttendances();
  }

  public static AttendanceRepository getInstance(Context context) {
    if (ourInstance == null) {
      ourInstance = new AttendanceRepository(context);
    }
    return ourInstance;
  }

  private LiveData<List<AttendanceEntity>> getAllAttendances() {
    return mDatabase.dao().getAll();
  }

  public Future insertEntityWithCheckInStatus(Location currentLocation) {
    Date date = new Date();
    AttendanceEntity attendanceEntity = new AttendanceEntity(
        date,
        date,
        currentLocation.getLatitude(),
        currentLocation.getLongitude(),
        new Date(0),
        0,
        0
    );
    return executorService.submit(() -> mDatabase.dao().insertOne(attendanceEntity));
  }

  public Future updateOne(AttendanceEntity attendanceEntity) {
    return executorService.submit(() -> mDatabase.dao().updateOne(attendanceEntity));
  }

  public Future<AttendanceEntity> getOneWhereCheckOutIsNull() {
    return executorService.submit(mDatabase.dao()::getOneWhereCheckOutIsNull);
  }
}
