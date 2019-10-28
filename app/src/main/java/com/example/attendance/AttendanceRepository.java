package com.example.attendance;

import android.content.Context;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class AttendanceRepository {
	private static AttendanceRepository ourInstance;
	public LiveData<List<AttendanceEntity>> mAttendances;
	private AttendanceDatabase mDatabase;
	private Executor mExecutor = Executors.newSingleThreadExecutor();
	
	private AttendanceRepository(Context context) {
		mDatabase = AttendanceDatabase.getInstance(context);
		mAttendances = getAllAttendances();
	}
	
	public static AttendanceRepository getInstance(Context context) {
		if (ourInstance == null) {
			ourInstance = new AttendanceRepository(context);
		}
		return ourInstance;
	}
	
	public void addSampleData() {
		final AttendanceEntity attendanceEntity = new AttendanceEntity(
				AttendanceEntity.AttendanceType.CHECK_IN,
				new Date(),
				123,
				123);
		//mAttendances = SampleDataProvider.getSampleData
		Executors.newSingleThreadExecutor()
				.invokeAny(()-> {
					long a = Thread.currentThread().getId();
				});
		
		mExecutor.execute(() -> {
			long a = Thread.currentThread().getId();
			mDatabase.dao().insertOne(attendanceEntity);
		});
	}
	
	private LiveData<List<AttendanceEntity>> getAllAttendances() {
		return mDatabase.dao().getAll();
	}
}
