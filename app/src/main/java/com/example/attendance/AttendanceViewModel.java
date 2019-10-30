package com.example.attendance;

import android.app.Application;

import java.util.List;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AttendanceViewModel extends AndroidViewModel {
	
	LiveData<List<AttendanceEntity>> mAttendances;
	private AttendanceRepository mRepository;
//	private Executor mExecutor = Executors.newSingleThreadExecutor();
//	private AttendanceDatabase mDatabase;
	
	public AttendanceViewModel(@NonNull Application application) {
		super(application);
		
		addSampleData();
		
		mRepository = AttendanceRepository.getInstance(application.getApplicationContext());
		mAttendances = mRepository.mAttendances;
	}
	
	private void addSampleData() {
		mRepository.addSampleData();
	}
}
