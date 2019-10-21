package com.example.attendance;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Attendance.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class, AttendanceTypeConverter.class})
public abstract class AttendanceDatabase extends RoomDatabase {
	public static final String DATABASE_NAME = "Attendance.db";
	private static final Object LOCK = new Object();
	public static volatile AttendanceDatabase databaseInstance;
	
	public static AttendanceDatabase getInstance(Context context) {
		if (databaseInstance == null) {
			synchronized (LOCK) {
				if (databaseInstance == null) {
					databaseInstance = Room.databaseBuilder(
							context.getApplicationContext(),
							AttendanceDatabase.class,
							DATABASE_NAME
					).build();
				}
			}
		}
		return databaseInstance;
	}
	
	public abstract AttendanceDao attendanceDao();
}
