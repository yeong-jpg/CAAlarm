package com.example.yun.caalarm;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static Subject[][] schedule = new Subject[9][6];

    DrawerLayout drawer;
    GridView mainGv;
    TextView userIdTv;
    ImageView menuBt, taggingBt, infoBt, alarmBt;
    MyAdapter myAdapter;
    ArrayList<Subject> viewData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout)findViewById(R.id.drawer);
        mainGv = (GridView) findViewById(R.id.mainGv);
        userIdTv = (TextView) findViewById(R.id.userIdTv);
        menuBt = (ImageView) findViewById(R.id.menuBt);
        taggingBt = (ImageView) findViewById(R.id.taggingBt);
        infoBt = (ImageView) findViewById(R.id.infoBt);
        alarmBt = (ImageView) findViewById(R.id.alarmBt);
        myAdapter = new MyAdapter(this);
        mainGv.setAdapter(myAdapter);
        init();

        menuBt.setOnClickListener(this);
        taggingBt.setOnClickListener(this);
        infoBt.setOnClickListener(this);
        alarmBt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.menuBt) {
            drawer.openDrawer(GravityCompat.START);
        } else if (v.getId() == R.id.taggingBt) {

        } else if (v.getId() == R.id.infoBt) {

        } else if (v.getId() == R.id.alarmBt) {

        }
    }


    /**
     * Adapter
     */
    public class RowDataViewHolder {
        public TextView subjectTvHolder;      //과목명
        public TextView locationTvHolder;     //수업 장소
    }

    class MyAdapter extends ArrayAdapter {
        LayoutInflater lnf;

        public MyAdapter(Activity context) {
            super(context, R.layout.subject, viewData);
            lnf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return viewData.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return viewData.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            RowDataViewHolder viewHolder;
            if (convertView == null) {
                convertView = lnf.inflate(R.layout.subject, parent, false);
                viewHolder = new RowDataViewHolder();
                viewHolder.subjectTvHolder = (TextView) convertView.findViewById(R.id.subjectTv);
                viewHolder.locationTvHolder = (TextView) convertView.findViewById(R.id.locationTv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (RowDataViewHolder) convertView.getTag();
            }

            if (position < 6 || position % 6 == 0) {
                viewHolder.subjectTvHolder.setTextSize(20);
                viewHolder.subjectTvHolder.setTextColor(Color.BLACK);
            }
            viewHolder.subjectTvHolder.setText(viewData.get(position).subject);
            //else
            //change text size
            viewHolder.locationTvHolder.setText(viewData.get(position).location);

//            int tempPos = player.posY + (player.posX * 5);


            return convertView;
        }
    }

    /**
     * Set Data
     */
    private void init() {

        //initialize the schedule
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[0].length; j++) {
                schedule[i][j] = new Subject(" ", " ");
            }
        }

        //set the day
        schedule[0][1] = new Subject("M", "");
        schedule[0][2] = new Subject("T", "");
        schedule[0][3] = new Subject("W", "");
        schedule[0][4] = new Subject("T", "");
        schedule[0][5] = new Subject("F", "");

        //set the period
        for (int i = 1; i < schedule.length; i++) {
            schedule[i][0] = new Subject(i + "", "");
        }

        //set the timetable
        //데이터 받아와서 넣는 부분

        //move data schedule(Array)->viewData(ArrayList)
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[0].length; j++) {
                viewData.add(schedule[i][j]);
            }
        }

//        viewData.add(new Subject("OODP", "NTH313"));
//        viewData.add(new Subject("OS","OH401"));
//        viewData.add(new Subject("Java","NTH413"));

        myAdapter.notifyDataSetChanged();
    }
}
