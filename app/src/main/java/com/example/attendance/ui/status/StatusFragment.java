package com.example.attendance.ui.status;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.attendance.AttendanceViewModel;
import com.example.attendance.R;
import com.example.attendance.ui.logs.LogsViewModel;

public class StatusFragment extends Fragment {
	
	private StatusViewModel statusViewModel;
	private LogsViewModel logsViewModel;
	private AttendanceViewModel attendanceViewModel;
	
	public View onCreateView(
			@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		attendanceViewModel = ViewModelProviders.of(this).get(AttendanceViewModel.class);
		
		logsViewModel = ViewModelProviders.of(this).get(LogsViewModel.class);
		logsViewModel.attendanceEntities.observe(this, attendanceEntities -> {
		});
		
		
		statusViewModel = ViewModelProviders.of(this).get(StatusViewModel.class);
		
		
		
		View root = inflater.inflate(R.layout.fragment_status, container, false);
		final TextView textView = root.findViewById(R.id.text_status);
		statusViewModel.getText().observe(this, textView::setText);
		return root;
	}
}