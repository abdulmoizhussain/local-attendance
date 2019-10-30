package com.example.attendance.ui.status;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.attendance.R;

public class StatusFragment extends Fragment {
	
	private StatusViewModel statusViewModel;
	
	public View onCreateView(
			@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		statusViewModel =
				ViewModelProviders.of(this).get(StatusViewModel.class);
		View root = inflater.inflate(R.layout.fragment_status, container, false);
		final TextView textView = root.findViewById(R.id.text_status);
		statusViewModel.getText().observe(this, textView::setText);
		return root;
	}
}