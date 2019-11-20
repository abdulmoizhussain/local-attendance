package com.example.attendance.ui.logs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendance.sqlite.context.AttendanceEntity;
import com.example.attendance.Common;
import com.example.attendance.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class LogsRecyclerViewAdapter extends RecyclerView.Adapter<LogsRecyclerViewAdapter.LogsRecyclerViewHolder> {
	private List<AttendanceEntity> attendanceEntities;

	LogsRecyclerViewAdapter(List<AttendanceEntity> attendanceEntities) {
		this.attendanceEntities = attendanceEntities;
	}

	@NonNull
	@Override
	public LogsRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
		View itemView = inflater.inflate(
				R.layout.recycler_view_logs_layout,
				viewGroup,
				false
		);

		//		return new LogsRecyclerViewHolder(itemView,
		//				new LogsRecyclerViewHolder.ViewHolderClicks() {
		//					@Override
		//					public void onCategoryClick(TextView ID, final TextView textViewCategoryName, final TextView name) {
		//					}
		//				});
		return new LogsRecyclerViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(LogsRecyclerViewHolder logsRecyclerViewHolder, int i) {
		AttendanceEntity entity = attendanceEntities.get(i);

		logsRecyclerViewHolder.textViewAttendanceId.setText(
				Common.FormatString("%d", entity.getId())
		);

		logsRecyclerViewHolder.textViewAttendanceDate.setText(
				Common.FormatDate(entity.getDate())
		);

		logsRecyclerViewHolder.textViewCheckInTime.setText(
				Common.FormatTime(entity.getCheckInTime())
		);

		String totalHoursString = "-", checkOutTime = "-";
		if (entity.getCheckOutTime().getTime() != 0) {
			double diff = entity.getCheckInTime().getTime() - entity.getCheckOutTime().getTime();
			diff /= 1000;// to seconds
			diff /= 60; // to minutes
			diff /= 60; // to hours
//			diff /= 24; // to days

//			https://stackoverflow.com/a/21596413
			diff = BigDecimal
					.valueOf(diff)
					.setScale(2, RoundingMode.HALF_UP)
					.doubleValue();

			totalHoursString = Double.toString(diff);

//			https://stackoverflow.com/a/11495691
//			int remainingMinutes = Integer.parseInt(
//					totalHoursWithFractionInString.split(".")[1]
//			);
//			remainingMinutes *= 60;
//			totalHoursString +=

			checkOutTime = Common.FormatTime(entity.getCheckOutTime());
		}

		logsRecyclerViewHolder.textViewCheckOutTime.setText(checkOutTime);
		logsRecyclerViewHolder.textViewTotalHoursWorked.setText(totalHoursString);
	}

	@Override
	public int getItemCount() {
		return attendanceEntities.size();
	}

	static class LogsRecyclerViewHolder extends RecyclerView.ViewHolder {
		//public class LogsRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		TextView textViewAttendanceId;
		TextView textViewAttendanceDate;
		TextView textViewCheckInTime;
		TextView textViewCheckOutTime;
		TextView textViewTotalHoursWorked;

		//	ViewHolderClicks mListener;

		//	LogsRecyclerViewHolder(View itemView, ViewHolderClicks listener) {
		LogsRecyclerViewHolder(View itemView) {
			super(itemView);
			//		this.mListener = listener;
			//		itemView.setOnClickListener(this);
			textViewAttendanceId = itemView.findViewById(R.id.textViewAttendanceId);
			textViewAttendanceDate = itemView.findViewById(R.id.textViewAttendanceDate);
			textViewCheckInTime = itemView.findViewById(R.id.textViewCheckInTime);
			textViewCheckOutTime = itemView.findViewById(R.id.textViewCheckOutTime);
			textViewTotalHoursWorked = itemView.findViewById(R.id.textViewTotalHoursWorked);

			setStyles(itemView);
		}

		private void setStyles(View view) {
			final int widthForATextView = LogsFragment.getWidthForATextView(view);

			textViewAttendanceDate.getLayoutParams().width = widthForATextView;
			textViewCheckInTime.getLayoutParams().width = widthForATextView;
			textViewCheckOutTime.getLayoutParams().width = widthForATextView;
			textViewTotalHoursWorked.getLayoutParams().width = widthForATextView;
		}

		//	@Override
		//	public void onClick(View view) {
		//		mListener.onCategoryClick(tvStatus, tvWorkerName, tvID);
		//	}

		//	public interface ViewHolderClicks {
		//		void onCategoryClick(TextView ID, TextView textViewURL, TextView TVName);
		//	}
	}
}

