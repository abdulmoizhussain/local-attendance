package com.example.attendance.ui.logs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.attendance.AttendanceEntity;
import com.example.attendance.R;
import com.example.attendance.ui.status.LogsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LogsFragment extends Fragment {
	private LogsViewModel logsViewModel;
	private RecyclerView logsRecyclerView;
	private RecyclerView.LayoutManager layoutManager;
	private LogsRecyclerViewAdapter logsRecyclerViewAdapter;
	private List<AttendanceEntity> attendanceEntities = new ArrayList<>();
	
	public View onCreateView(
			@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_logs, container, false);
		
		logsViewModel = ViewModelProviders.of(this).get(LogsViewModel.class);
		
		final TextView textView = root.findViewById(R.id.text_logs);
		logsViewModel.getText().observe(this, textView::setText);
		
		logsViewModel.attendanceEntities.observe(this, (List<AttendanceEntity> attendanceEntities) -> {
			this.attendanceEntities.clear();
			if (attendanceEntities != null)
				this.attendanceEntities.addAll(attendanceEntities);
		});
		
		return root;
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		initRecyclerView(view);
	}
	
	private void initRecyclerView(@NonNull View view) {
		logsRecyclerView = view.findViewById(R.id.recycler_view_logs);
		logsRecyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(view.getContext());
		logsRecyclerView.setLayoutManager(layoutManager);
		logsRecyclerViewAdapter = new LogsRecyclerViewAdapter(attendanceEntities);
		logsRecyclerView.setAdapter(logsRecyclerViewAdapter);
	}
}