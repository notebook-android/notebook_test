package com.example.notebook_test.Fregment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.style.ForegroundColorSpan;
import android.text.style.LineBackgroundSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.notebook_test.Model.MyCalendarBean;
import com.example.notebook_test.Model.Schedule;
import com.example.notebook_test.R;
import com.example.notebook_test.datepicker.DateFormatUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link calender.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link calender#newInstance} factory method to
 * create an instance of this fragment.
 */
public class calender extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MaterialCalendarView cv;
    private OnFragmentInteractionListener mListener;

    public calender() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment calender.
     */
    // TODO: Rename and change types and number of parameters
    public static calender newInstance(String param1, String param2) {
        calender fragment = new calender();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calender,container,false);
        Bundle args = getArguments();
        //测试可用于从其他act中传值
//        if (args != null) {
//            context = args.getString("context");
//        }


        //listview的事件

        final List<String> items = new ArrayList<String>(); //用来存储数据库的数据

        List<Schedule> item = LitePal.select("startTime").find(Schedule.class);

        for(Schedule schedule:item){
            //此处数据库修改后 修正报错
            Log.d("asda**************", "onCreateView: "+ DateFormatUtils.long2Str(schedule.getStartTime(),true));

            SimpleDateFormat sf1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
            Date date = null;

            //此处修改
            //date = sf1.parse(schedule.getStartTime().toString());
            date = new Date(schedule.getStartTime());

            SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
            Log.d("1111:",sf2.format(date));
            items.add(sf2.format(date));
        }

        ListView listView =  view.findViewById(R.id.listView1); // 从布局中获取listview，也可以动态创建
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1, items));//关联Adapter//将ListView加到适配器里
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //设置点击ListView中的条目的响应对象
            @Override
            public void onItemClick(AdapterView<?> parent, View view, //响应方法，其中view是一个TextView对象，position是选择条目的序号
                                    int position, long id) {
                Toast.makeText(getActivity(), (CharSequence) items.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        //日历的响应事件
        cv = view.findViewById(R.id.cd_calendarView);

        //设置第一天是周几
        cv.state().edit().setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2015,7,17))
                .setMaximumDate(CalendarDay.from(2025,7,17))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        //获取当前时间
//        Calendar calendar = Calendar.getInstance();
//        cv.setSelectedDate(calendar.getTime());
//        cv.setSelectionColor(getResources().getColor(R.color.colorPrimary));

        //给当前时间添加标记
        cv.addDecorators(new NowDate());

        //给事件添加标记
        cv.addDecorators(new HighlightWeekendsDecorator(), new SameDayDecorator());

        cv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.d("asd",date.toString());
            }
        });


        return view;
    }
//结束
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}


//给周末日期设置特殊字体颜色：
class HighlightWeekendsDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.parseColor("#fd755c")));
    }
}
//打红点
class SameDayDecorator implements DayViewDecorator {
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        Date date = new Date();
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//        String dateStr=sdf.format(date);


        String str="2019-7-18";
        SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd");

        Date parse= null;
        try {
            parse = sdf1.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Date parse = TimeUtils.string2Date(dateStr, "yyyy-MM-dd");
        if (day.getDate().equals(parse)) {
            return true;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new CircleBackGroundSpan());
    }
}

class CircleBackGroundSpan implements LineBackgroundSpan {
    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#fd755c"));
        c.drawCircle((right - left) / 2, (bottom - top) / 2+40 , 8, paint);
    }
}


//给当前时间设置背景
class NowDate implements DayViewDecorator {
    //private MaterialCalendarView cv;
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        Calendar calendar = Calendar.getInstance();//获取一个calendar

        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String str =sdf.format(calendar.getTime());//calendar.getTime是获取当前时间

        //String和Date之间的转换
        SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd");

        Date parse= null;
        try {
            parse = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Date parse = TimeUtils.string2Date(dateStr, "yyyy-MM-dd");
        if (day.getDate().equals(parse)) {
            return true;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new NowCircleBackGroundSpan());
    }
}

class NowCircleBackGroundSpan implements LineBackgroundSpan {
    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#87CEFA"));
        c.drawCircle((right - left) / 2, (bottom - top) / 2 , 40, paint);
    }
}