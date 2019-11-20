package com.example.attendance.sqlite.context;

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
  private double checkInLatitude;

  @ColumnInfo(name = "CheckInLongitude")
  private double checkInLongitude;

  @ColumnInfo(name = "CheckOutTime")
  private Date checkOutTime;

  @ColumnInfo(name = "CheckOutLatitude")
  private double checkOutLatitude;

  @ColumnInfo(name = "CheckOutLongitude")
  private double checkOutLongitude;

  // array of break-times.

  public AttendanceEntity(Date date,
                          Date checkInTime,
                          double checkInLatitude,
                          double checkInLongitude,
                          Date checkOutTime,
                          double checkOutLatitude,
                          double checkOutLongitude) {
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

  public double getCheckInLatitude() {
    return checkInLatitude;
  }

  public void setCheckInLatitude(double checkInLatitude) {
    this.checkInLatitude = checkInLatitude;
  }

  public double getCheckInLongitude() {
    return checkInLongitude;
  }

  public void setCheckInLongitude(double checkInLongitude) {
    this.checkInLongitude = checkInLongitude;
  }

  public Date getCheckOutTime() {
    return checkOutTime;
  }

  public void setCheckOutTime(Date checkOutTime) {
    this.checkOutTime = checkOutTime;
  }

  public double getCheckOutLatitude() {
    return checkOutLatitude;
  }

  public void setCheckOutLatitude(double checkOutLatitude) {
    this.checkOutLatitude = checkOutLatitude;
  }

  public double getCheckOutLongitude() {
    return checkOutLongitude;
  }

  public void setCheckOutLongitude(double checkOutLongitude) {
    this.checkOutLongitude = checkOutLongitude;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
