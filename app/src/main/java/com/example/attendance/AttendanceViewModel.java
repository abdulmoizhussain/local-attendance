package com.example.attendance;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Future;

public class AttendanceViewModel extends AndroidViewModel {

  LiveData<List<AttendanceEntity>> mAttendances;
  private AttendanceRepository mRepository;
//	private Executor mExecutor = Executors.newSingleThreadExecutor();

  public AttendanceViewModel(@NonNull Application application) {
    super(application);

    mRepository = AttendanceRepository.getInstance(application.getApplicationContext());
    mAttendances = mRepository.allAttendances;

    addSampleData();
  }

  private void addSampleData() {
    mRepository.addSampleData();
  }

  public Future<AttendanceEntity> getOneWhereCheckOutLocationIsNull() {
    return mRepository.getOneWhereCheckOutLocationIsNull();
  }
}
