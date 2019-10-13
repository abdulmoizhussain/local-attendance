package com.example.attendance;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AttendanceViewModel extends AndroidViewModel {
	
	public List<Attendance> mAttendances;
	public Repository mRepository;
	
	public AttendanceViewModel(@NonNull Application application) {
		super(application);
		mRepository
	}
	
}
