package com.example.attendance;

import android.content.Context;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AttendanceRepository {
	
	private static AttendanceRepository ourInstance;
	public List<Attendance> mAttendances;
	private AttendanceDatabase mDatabase;
	private Executor mExecutor = Executors.newSingleThreadExecutor();
	
	private AttendanceRepository(Context context) {
		mDatabase = AttendanceDatabase.getInstance(context);
	}
	
	public static AttendanceRepository getInstance(Context context) {
		ourInstance = new AttendanceRepository(context);
		return ourInstance;
	}
	
	public void addSampleData() {
		final Attendance attendance = new Attendance(
				Attendance.Type.CHECK_IN,
				new Date(),
				123,
				123);
		//mAttendances = SampleDataProvider.getSampleData
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				mDatabase.attendanceDao().insertOne(attendance);
			}
		});
	}
}
