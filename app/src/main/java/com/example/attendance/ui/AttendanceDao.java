package com.example.attendance.ui;

import com.example.attendance.Attendance;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface AttendanceDao {
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertOne(Attendance attendance);
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertAll(List<Attendance> attendances);
	
	@Delete
	void deleteOne(Attendance attendance);
	
	@Query("SELECT * FROM Attendance ORDER BY date DESC")
	List<Attendance> getAll();
	
	@Query("SELECT * FROM Attendance WHERE id=:id")
	List<Attendance> getOneById(int id);
	
	
	@Query("DELETE FROM Attendance")
	int deleteAll();
	
	@Query("SELECT COUNT(*) FROM Attendance")
	int getCount();
}
