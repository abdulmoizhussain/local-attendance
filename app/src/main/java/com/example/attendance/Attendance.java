package com.example.attendance;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Attendance")
public class Attendance {
	
	@PrimaryKey(autoGenerate = true)
	private int id;
	@ColumnInfo(name = "type")
	private Type type;
	@ColumnInfo(name = "date")
	private Date date;
	@ColumnInfo(name = "latitude")
	private float latitude;
	@ColumnInfo(name = "longitude")
	private float longitude;
	
	public Attendance(Type type, Date date, float latitude, float longitude) {
		this.type = type;
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
	
	public Type getType() {
		return this.type;
	}
	
	public void setType(Type type) {
		this.type = type;
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
	
	public enum Type {CHECK_OUT, CHECK_IN}
}

