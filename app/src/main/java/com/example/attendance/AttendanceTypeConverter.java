package com.example.attendance;

import androidx.room.TypeConverter;

public class AttendanceTypeConverter {
	@TypeConverter
	public static String toTypeString(Attendance.Type type) {
		return type == null ? null : type.name();
	}
	
	@TypeConverter
	public static Attendance.Type fromTypeString(String type) {
		return type == null ? null : Attendance.Type.valueOf(type);
	}
}
