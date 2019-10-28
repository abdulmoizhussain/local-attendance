package com.example.attendance;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface IAttendanceDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertOne(AttendanceEntity attendanceEntity);
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertAll(List<AttendanceEntity> attendanceEntities);
	
	@Delete
	void deleteOne(AttendanceEntity attendanceEntity);
	
	@Query("SELECT * FROM AttendanceEntity ORDER BY date DESC")
	LiveData<List<AttendanceEntity>> getAll();
	
	@Query("SELECT * FROM AttendanceEntity WHERE id=:id")
	List<AttendanceEntity> getOneById(int id);
	
	@Query("DELETE FROM AttendanceEntity")
	int deleteAll();
	
	@Query("SELECT COUNT(*) FROM AttendanceEntity")
	int getCount();
}
