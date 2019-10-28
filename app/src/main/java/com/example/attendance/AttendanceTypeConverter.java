package com.example.attendance;

import androidx.room.TypeConverter;

public class AttendanceTypeConverter {
	@TypeConverter
	public static String toTypeString(AttendanceEntity.AttendanceType attendanceType) {
		return attendanceType == null ? null : attendanceType.name();
	}
	
	@TypeConverter
	public static AttendanceEntity.AttendanceType fromTypeString(String type) {
		return type == null ? null : AttendanceEntity.AttendanceType.valueOf(type);
	}
}
