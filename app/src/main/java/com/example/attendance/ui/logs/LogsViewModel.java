package com.example.attendance.ui.logs;

import android.app.Application;

import com.example.attendance.AttendanceEntity;
import com.example.attendance.AttendanceRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LogsViewModel extends AndroidViewModel {
	
	LiveData<List<AttendanceEntity>> attendanceEntities;
	private MutableLiveData<String> mText;
	private AttendanceRepository attendanceRepository;
	
	public LogsViewModel(@NonNull Application application) {
		super(application);
		mText = new MutableLiveData<>();
		
		attendanceRepository = AttendanceRepository.getInstance(application);
		attendanceEntities = attendanceRepository.mAttendances;
		mText.setValue("This is logs fragment");
	}
	
	
	public LiveData<String> getText() {
		return mText;
	}
}