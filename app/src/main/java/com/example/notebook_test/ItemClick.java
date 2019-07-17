package com.example.notebook_test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.notebook_test.Model.Schedule;
import com.example.notebook_test.datepicker.DateFormatUtils;

public class ItemClick extends Activity {
    private Schedule schedule;
    private ImageView Type;
    private TextView Title;
    private TextView Content;
    private TextView Creat;
    private TextView Start;
    private TextView End;
    private TextView IfAllDay;
    private TextView Repetition;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchitems_click);
        initView();

    }
    private void initView(){
        schedule = (Schedule)getIntent().getSerializableExtra("Schedule");
        Type = (ImageView)findViewById(R.id.click_type);
        Type.setImageResource(getImageId(schedule.getType()));
        Title = (TextView) findViewById(R.id.click_title);
        Title.setText(schedule.getTitle());
        Content = (TextView) findViewById(R.id.click_content);
        Content.setText(schedule.getContent());
        Creat = (TextView) findViewById(R.id.click_creattime);
        Creat.setText(DateFormatUtils.long2Str(schedule.getCreateTime(),false));
        Start = (TextView) findViewById(R.id.click_starttime);
        Start.setText(DateFormatUtils.long2Str(schedule.getStartTime(),false));
        End = (TextView) findViewById(R.id.click_endtime);
        End.setText(DateFormatUtils.long2Str(schedule.getFinishTime(),false));
        IfAllDay = (TextView) findViewById(R.id.click_if_allday);
        Repetition = (TextView) findViewById(R.id.click_Reptition);
        if(schedule.isAllDay()){
            IfAllDay.setText("全天");
        }else {
            IfAllDay.setText("非全天");
        }
        switch (schedule.getRepetition()){
            case 0:Repetition.setText("永不");break;
            case 1:Repetition.setText("每天");break;
            case 2:Repetition.setText("每周");break;
            case 3:Repetition.setText("每两周");break;
            case 4:Repetition.setText("每月");break;
            case 5:Repetition.setText("每年");break;
        }
    }
    public int getImageId(int id){
        if(id==0)
            return R.drawable.ic_work_black_48dp;
        else if(id==1)
            return R.drawable.ic_home_black_48dp;
        else if(id==2)
            return R.drawable.ic_directions_run_black_48dp;
        else
            return R.drawable.ic_flight_takeoff_black_48dp;
    }
}
