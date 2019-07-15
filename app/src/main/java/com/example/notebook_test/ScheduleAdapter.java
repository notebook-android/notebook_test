package com.example.notebook_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.notebook_test.Model.Schedule;

import java.util.List;

public class ScheduleAdapter extends ArrayAdapter<Schedule> {
    private int resourceId;

    public ScheduleAdapter(Context context, int textViewResourceId, List<Schedule> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Schedule schedule = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView title = (TextView) view.findViewById(R.id.title_view);
        TextView content = (TextView) view.findViewById(R.id.content_view);
        TextView time = (TextView) view.findViewById(R.id.time_view);
        title.setText(schedule.getTitle());
        content.setText(schedule.getContent());
        time.setText(schedule.getTitle());
        return view;
    }
}
