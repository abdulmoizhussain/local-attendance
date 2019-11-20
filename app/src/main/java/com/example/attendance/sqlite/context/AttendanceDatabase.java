package com.example.attendance.sqlite.context;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {AttendanceEntity.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AttendanceDatabase extends RoomDatabase {
	public static final String DATABASE_NAME = "attendance.db";
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
	
	// DAO: Data Access Object
	public abstract IAttendanceDao dao();
}
