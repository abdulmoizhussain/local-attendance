package com.example.attendance;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AttendanceRepository {
  private static AttendanceRepository ourInstance;
  private AttendanceDatabase mDatabase;
  private Executor mExecutor = Executors.newSingleThreadExecutor();
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

  void addSampleData() {
    Date date = new Date();
    final AttendanceEntity attendanceEntity = new AttendanceEntity(
        date,
        date,
        123,
        123,
        null,
        0,
        0);

//		Executors.newSingleThreadExecutor()
//				.invokeAny(()-> {
//					long a = Thread.currentThread().getId();
//				});

    mExecutor.execute(() -> mDatabase.dao().insertOne(attendanceEntity));
  }

  private LiveData<List<AttendanceEntity>> getAllAttendances() {
    return mDatabase.dao().getAll();
  }


  Future<AttendanceEntity> getOneWhereCheckOutLocationIsNull() {
    return executorService.submit(mDatabase.dao()::getOneWhereCheckOutLocationIsNull);
  }
}
