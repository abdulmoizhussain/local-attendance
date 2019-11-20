package com.example.attendance.sqlite.context;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface IAttendanceDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertOne(AttendanceEntity attendanceEntity);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAll(List<AttendanceEntity> attendanceEntities);

  @Delete
  void deleteOne(AttendanceEntity attendanceEntity);

  @Update
  void updateOne(AttendanceEntity attendanceEntity);

  @Query("SELECT * FROM AttendanceEntity ORDER BY Date DESC")
  LiveData<List<AttendanceEntity>> getAll();

  @Query("SELECT * FROM AttendanceEntity WHERE id=:id")
  List<AttendanceEntity> getOneById(int id);

  @Query("SELECT * FROM AttendanceEntity WHERE CheckOutTime=0 AND CheckOutLatitude=0 AND CheckOutLongitude=0")
  AttendanceEntity getOneWhereCheckOutIsNull();

  @Query("DELETE FROM AttendanceEntity")
  int deleteAll();

  @Query("SELECT COUNT(*) FROM AttendanceEntity")
  int getCount();
}
