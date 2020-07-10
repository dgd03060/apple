package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsListAdapter extends BaseAdapter{
    private Context context;
    private List<News> newsList;
    private Activity parentActivity;
    private List<News> saveList;
    private Button linkButton;



    public NewsListAdapter(Context context, List<News> newsList, Activity parentActivity, List<News> saveList) {
        this.context = context;
        this.newsList = newsList;
        this.parentActivity = parentActivity;
        this.saveList = saveList;//검색
        this.parentActivity = parentActivity;


    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override

    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.news, null);




        //뷰에 다음 컴포넌트들을 연결시켜줌

        //final추가 안붙이면 에러남 리스너로 전달하고 싶은 지역변수는 final로 처리해야됨

        final TextView courseTitle = (TextView) v.findViewById(R.id.courseTitle);
        TextView courseDivide = (TextView) v.findViewById(R.id.courseDivide);
        TextView courseGrade = (TextView) v.findViewById(R.id.courseGrade);
        TextView courseID = (TextView)v.findViewById(R.id.courseRank);
        final ImageView courseImage = (ImageView) v.findViewById(R.id.image);
        TextView coursebefre = (TextView)v.findViewById(R.id.coursebefore);


        final String imageUrl = newsList.get(i).getCourseImage();
        Glide.with(parentActivity).load(imageUrl).into(courseImage);


        courseID.setText(newsList.get(i).getCourseID().substring(0, 2));
        courseTitle.setText(newsList.get(i).getCourseUniversity());
        courseDivide.setText(newsList.get(i).getCourseMajor());
        courseGrade.setText(newsList.get(i).getCourseArea());
        coursebefre.setText(newsList.get(i).getCourseTerm());









        courseTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, Newspop.class);
                parentActivity.startActivity(intent);
            }
        });
        return v;
    }
}

