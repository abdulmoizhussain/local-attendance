package com.example.attendance.sqlite.context;

import java.util.Date;

import androidx.room.TypeConverter;

public class DateConverter {
	
	@TypeConverter
	public static Long toTimeStamp(Date date) {
		return date == null ? null : date.getTime();
	}
	
	@TypeConverter
	public static Date fromTimeStamp(Long timeStamp) {
		return timeStamp == null ? null : new Date(timeStamp);
	}
}
