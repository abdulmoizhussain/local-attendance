package com.example.attendance.ui.logs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.attendance.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class LogsFragment extends Fragment {
	
	private LogsViewModel logsViewModel;
	
	public View onCreateView(
			@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		logsViewModel = ViewModelProviders.of(this).get(LogsViewModel.class);
		
		View root = inflater.inflate(R.layout.fragment_logs, container, false);
		
		final TextView textView = root.findViewById(R.id.text_logs);
		logsViewModel.getText().observe(this, textView::setText);
		
		return root;
	}
}