package com.example.attendance;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AttendanceEntity")
public class AttendanceEntity {
	
	@PrimaryKey(autoGenerate = true)
	private int id;
	@ColumnInfo(name = "attendanceType")
	private AttendanceType attendanceType;
	@ColumnInfo(name = "date")
	private Date date;
	@ColumnInfo(name = "latitude")
	private float latitude;
	@ColumnInfo(name = "longitude")
	private float longitude;
	
	public AttendanceEntity(AttendanceType attendanceType, Date date, float latitude, float longitude) {
		this.attendanceType = attendanceType;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public AttendanceType getAttendanceType() {
		return this.attendanceType;
	}
	
	public void setAttendanceType(AttendanceType attendanceType) {
		this.attendanceType = attendanceType;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public float getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	public float getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	public enum AttendanceType {CHECK_OUT, CHECK_IN}
}
