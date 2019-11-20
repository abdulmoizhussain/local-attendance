package com.example.attendance.ui.logs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.attendance.sqlite.context.AttendanceEntity;
import com.example.attendance.repository.sqlite.AttendanceRepository;

import java.util.List;

public class LogsViewModel extends AndroidViewModel {

	public LiveData<List<AttendanceEntity>> attendanceEntities;

	private AttendanceRepository attendanceRepository;

	public LogsViewModel(@NonNull Application application) {
		super(application);
		attendanceRepository = AttendanceRepository.getInstance(application);
		attendanceEntities = attendanceRepository.allAttendances;
	}

	//	private MutableLiveData<String> mText;
//	mText = new MutableLiveData<>();
//	mText.setValue("This is logs fragment");
//	public LiveData<String> getText() {
//		return mText;
//	}
//	usage
//	final TextView textView = root.findViewById(R.id.textViewHeadDate);
//		logsViewModel.getText().observe(this, textView::setText);
}