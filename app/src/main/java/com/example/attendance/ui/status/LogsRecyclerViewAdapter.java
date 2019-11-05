package com.example.attendance.ui.status;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendance.AttendanceEntity;
import com.example.attendance.Common;
import com.example.attendance.R;

import java.util.List;

public class LogsRecyclerViewAdapter extends RecyclerView.Adapter<LogsRecyclerViewAdapter.LogsRecyclerViewHolder> {
  private List<AttendanceEntity> attendanceEntities;

  public LogsRecyclerViewAdapter(List<AttendanceEntity> attendanceEntities) {
    this.attendanceEntities = attendanceEntities;
  }

  @NonNull
  @Override
  public LogsRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
    View itemView = inflater.inflate(R.layout.recycler_view_logs_layout, viewGroup, false);

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

    logsRecyclerViewHolder.attId.setText(Common.FormatString("%d", entity.getId()));
    logsRecyclerViewHolder.attType.setText(entity.getAttendanceType().name());

    String location = Common.FormatString(
        "%f:%f",
        entity.getLatitude(),
        entity.getLongitude());
    logsRecyclerViewHolder.attLocation.setText(location);

    logsRecyclerViewHolder.attDate.setText(entity.getDate().toString());
  }

  @Override
  public int getItemCount() {
    return attendanceEntities.size();
  }

  static class LogsRecyclerViewHolder extends RecyclerView.ViewHolder {
    //public class LogsRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView attId;
    TextView attType;
    TextView attDate;
    TextView attLocation;

    //	ViewHolderClicks mListener;

    //	LogsRecyclerViewHolder(View itemView, ViewHolderClicks listener) {
    LogsRecyclerViewHolder(View itemView) {
      super(itemView);
      //		this.mListener = listener;
      //		itemView.setOnClickListener(this);
      attId = itemView.findViewById(R.id.attendance_id);
      attType = itemView.findViewById(R.id.attendance_type);
      attDate = itemView.findViewById(R.id.attendance_date);
      attLocation = itemView.findViewById(R.id.attendance_location);
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

