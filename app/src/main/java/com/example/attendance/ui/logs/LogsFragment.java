package com.example.attendance.ui.logs;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.attendance.Common;
import com.example.attendance.sqlite.context.AttendanceEntity;
import com.example.attendance.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LogsFragment extends Fragment {
	private TextView textViewHeadDate, textViewHeadClockInTime, textViewHeadClockOutTime,
			textViewHeadTotalHours;
	private LogsViewModel logsViewModel;
	private List<AttendanceEntity> attendanceEntities = new ArrayList<>();
	private RecyclerView recyclerViewLogs;

	public View onCreateView(
			@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_logs, container, false);

		logsViewModel = ViewModelProviders.of(this).get(LogsViewModel.class);

		textViewHeadDate = root.findViewById(R.id.textViewHeadDate);
		textViewHeadClockInTime = root.findViewById(R.id.textViewHeadClockInTime);
		textViewHeadClockOutTime = root.findViewById(R.id.textViewHeadClockOutTime);
		textViewHeadTotalHours = root.findViewById(R.id.textViewHeadTotalHours);

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
		setStyles(view);
	}

	private void initRecyclerView(@NonNull View view) {
		recyclerViewLogs = view.findViewById(R.id.recyclerViewLogs);
		recyclerViewLogs.setHasFixedSize(false);

		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
		recyclerViewLogs.setLayoutManager(layoutManager);

		LogsRecyclerViewAdapter logsRecyclerViewAdapter = new LogsRecyclerViewAdapter(attendanceEntities);
		recyclerViewLogs.setAdapter(logsRecyclerViewAdapter);
	}

	private void setStyles(View view) {
		final int widthForATextView = getWidthForATextView(view);
		ViewGroup.LayoutParams layoutParams;

		layoutParams = textViewHeadDate.getLayoutParams();
		layoutParams.width = widthForATextView;

		layoutParams = textViewHeadDate.getLayoutParams();
		layoutParams.width = widthForATextView;

		layoutParams = textViewHeadClockInTime.getLayoutParams();
		layoutParams.width = widthForATextView;

		layoutParams = textViewHeadClockOutTime.getLayoutParams();
		layoutParams.width = widthForATextView;

		layoutParams = textViewHeadTotalHours.getLayoutParams();
		layoutParams.width = widthForATextView;

		int currentHeight = recyclerViewLogs.getHeight();
		int heightToSubtract = textViewHeadClockOutTime.getHeight();

		recyclerViewLogs
				.getLayoutParams()
				.height = currentHeight - heightToSubtract;
		recyclerViewLogs.requestLayout();
	}

	static int getWidthForATextView(View view) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity) view.getContext())
				.getWindowManager()
				.getDefaultDisplay()
				.getMetrics(displayMetrics);

		final int windowWidth = displayMetrics.widthPixels;
//		final int windowHeight = displayMetrics.heightPixels;

		// 8*5, coz there are 4 textViews and all 4 have left-margins of 8dp,
		// and margin-right of 4dp for last textView.
		return (windowWidth - (8 * 5)) / 4;
	}
}