package com.example.attendance;

import android.app.Application;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AttendanceViewModel extends AndroidViewModel {
	
	public List<Attendance> mAttendances;
	public AttendanceRepository mRepository;
	private Executor mExecutor = Executors.newSingleThreadExecutor();
	private AttendanceDatabase mDatabase;
	
	public AttendanceViewModel(@NonNull Application application) {
		super(application);
		mRepository = AttendanceRepository.getInstance(application.getApplicationContext());
		mAttendances = mRepository.mAttendances;
	}
	
	public void addSampleData() {
//		mRepository.addSampleData();
	}
}
