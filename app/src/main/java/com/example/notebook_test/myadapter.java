package com.example.notebook_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.notebook_test.Model.Schedule;

import java.util.List;



/**

 * Created by lw on 2017/4/14.

 */
public class myadapter extends ArrayAdapter{

    private final int resourceId;

    public myadapter(Context context, int textViewResourceId, List<Schedule> objects) {

        super(context, textViewResourceId, objects);

        resourceId = textViewResourceId;

    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        Schedule schedule = (Schedule) getItem(position); // 获取当前项的Fruit实例

        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent,false);//实例化一个对象

        TextView TitleName = (TextView) view.findViewById(R.id.title_view);//获取该布局内的文本视图

        TitleName.setText(schedule.getTitle());//为文本视图设置文本内容

        return view;

    }

}

