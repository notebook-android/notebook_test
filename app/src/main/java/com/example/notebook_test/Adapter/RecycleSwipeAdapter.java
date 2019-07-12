package com.example.notebook_test.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.notebook_test.Model.Note;
import com.example.notebook_test.Model.Date;
import com.example.notebook_test.R;

import java.util.List;


/**
 * 回收站适配器
 */

public class RecycleSwipeAdapter extends BaseAdapter {


    private List<Note> mData ;
    private Context mContext;

    private Date nowDate;
    public RecycleSwipeAdapter(Context mContext, List<Note> mData) {
        this.mContext = mContext;
        this.mData = mData;
        nowDate = new Date();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(null == convertView) {
            convertView = View.inflate(mContext, R.layout.item_recycle, null);
        }
        Note note = ((Note)getItem(position));

        TextView name =(TextView)convertView.findViewById(R.id.name_item);
        TextView deleteDate = (TextView) convertView.findViewById(R.id.date_item);
        name.setText(note.getName());

        int days=nowDate.getLeaveDay(note.getDate());
        String leaveDay = Integer.toString(29-days)+"天";

        if(deleteDate!=null)deleteDate.setText(leaveDay);

        return convertView;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
