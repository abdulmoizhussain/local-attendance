package com.example.attendance;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "AttendanceEntity")
public class AttendanceEntity {

  @PrimaryKey(autoGenerate = true)
  private int id;

  @ColumnInfo(name = "Date")
  private Date date;

  @ColumnInfo(name = "CheckInTime")
  private Date checkInTime;

  @ColumnInfo(name = "CheckInLatitude")
  private float checkInLatitude;

  @ColumnInfo(name = "CheckInLongitude")
  private float checkInLongitude;

  @ColumnInfo(name = "CheckOutTime")
  private Date checkOutTime;

  @ColumnInfo(name = "CheckOutLatitude")
  private float checkOutLatitude;

  @ColumnInfo(name = "CheckOutLongitude")
  private float checkOutLongitude;

  // array of break-times.

  public AttendanceEntity(Date date,
                          Date checkInTime,
                          float checkInLatitude,
                          float checkInLongitude,
                          Date checkOutTime,
                          float checkOutLatitude,
                          float checkOutLongitude) {
    this.date = date;
    this.checkInTime = checkInTime;
    this.checkOutTime = checkOutTime;
    this.checkInLatitude = checkInLatitude;
    this.checkInLongitude = checkInLongitude;
    this.checkOutLatitude = checkOutLatitude;
    this.checkOutLongitude = checkOutLongitude;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getCheckInTime() {
    return checkInTime;
  }

  public void setCheckInTime(Date checkInTime) {
    this.checkInTime = checkInTime;
  }

  public float getCheckInLatitude() {
    return checkInLatitude;
  }

  public void setCheckInLatitude(float checkInLatitude) {
    this.checkInLatitude = checkInLatitude;
  }

  public float getCheckInLongitude() {
    return checkInLongitude;
  }

  public void setCheckInLongitude(float checkInLongitude) {
    this.checkInLongitude = checkInLongitude;
  }

  public Date getCheckOutTime() {
    return checkOutTime;
  }

  public void setCheckOutTime(Date checkOutTime) {
    this.checkOutTime = checkOutTime;
  }

  public float getCheckOutLatitude() {
    return checkOutLatitude;
  }

  public void setCheckOutLatitude(float checkOutLatitude) {
    this.checkOutLatitude = checkOutLatitude;
  }

  public float getCheckOutLongitude() {
    return checkOutLongitude;
  }

  public void setCheckOutLongitude(float checkOutLongitude) {
    this.checkOutLongitude = checkOutLongitude;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
