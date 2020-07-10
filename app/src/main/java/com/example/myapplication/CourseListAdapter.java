package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CourseListAdapter extends BaseAdapter{
    private final Activity parentActivity;
    private Context context;
    private List<Course> courseList;
    private Activity parent;

    public CourseListAdapter(Context context, List<Course> courseList, Activity parentActivity) {
        this.context = context;
        this.courseList = courseList;
        this.parentActivity = parentActivity;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);//해당 위치의 값을 리스트뷰에 뿌려줌
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View View, final ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.course, null);
        final TextView courseGrade = (TextView) v.findViewById(R.id.courseGrade);
        TextView courseTitle = (TextView) v.findViewById(R.id.courseTitle);
        final TextView courseCredit = (TextView) v.findViewById(R.id.courseCredit);
        final TextView courseDivide = (TextView) v.findViewById(R.id.courseDivide);
        final TextView coursePersonnel = (TextView) v.findViewById(R.id.coursePersonnel);
        TextView courseProfessor = (TextView) v.findViewById(R.id.courseProfessor);
        final TextView courseUniversity = (TextView) v.findViewById(R.id.courseplace);
        final TextView courseTerm = (TextView) v.findViewById(R.id.courseparea);
        final TextView courseInfo = (TextView)v.findViewById(R.id.courseinfo);
        final TextView courseTime = (TextView)v.findViewById(R.id.courseTime);
        final TextView detail = (TextView)v.findViewById(R.id.detailtext);
        final TextView detail2 = (TextView)v.findViewById(R.id.detailtext2);
        final TextView courseMj = (TextView)v.findViewById(R.id.courseMj);
        final TextView courseMj2 = (TextView)v.findViewById(R.id.courseMj2);

        final ImageView courseImage = (ImageView)v.findViewById(R.id.courseImage);

        final String imageUrl = courseList.get(position).getCourseImage();
        Glide.with(parentActivity).load(imageUrl).into(courseImage);

        courseGrade.setText("날짜 : " + courseList.get(position).getCourseGrade());
        courseTitle.setText(courseList.get(position).getCourseTitle());
        courseCredit.setText("시간 : " + courseList.get(position).getCourseCredit());
        courseDivide.setText("날짜 : " + courseList.get(position).getCourseDivide());
        coursePersonnel.setText("시간 : " + courseList.get(position).getCoursePersonnel());
        courseProfessor.setText(courseList.get(position).getCourseProfessor());
        courseUniversity.setText(courseList.get(position).getCourseUniversity());
        courseTerm.setText(courseList.get(position).getCourseTerm());
        courseMj.setText(courseList.get(position).getCourseArea() + " >");
        courseMj2.setText(courseList.get(position).getCourseMajor());

        courseGrade.setVisibility(View.GONE);
        courseCredit.setVisibility(View.GONE);
        courseDivide.setVisibility(View.GONE);
        coursePersonnel.setVisibility(View.GONE);
        courseUniversity.setVisibility(View.GONE);
        courseTerm.setVisibility(View.GONE);
        courseTime.setVisibility(View.GONE);
        courseInfo.setVisibility(View.GONE);
        detail2.setVisibility(View.GONE);

        final String URL = courseList.get(position).getCourseRoom();


        v.setTag(courseList.get(position).getCourseID());

        detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                courseGrade.setVisibility(View.VISIBLE);
                courseCredit.setVisibility(View.VISIBLE);
                courseDivide.setVisibility(View.VISIBLE);
                coursePersonnel.setVisibility(View.VISIBLE);
                courseUniversity.setVisibility(View.VISIBLE);
                courseTerm.setVisibility(View.VISIBLE);
                courseTime.setVisibility(View.VISIBLE);
                courseInfo.setVisibility(View.VISIBLE);
                detail.setVisibility(View.GONE);
                detail2.setVisibility(View.VISIBLE);

            }
        });


        detail2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                courseGrade.setVisibility(View.GONE);
                courseCredit.setVisibility(View.GONE);
                courseDivide.setVisibility(View.GONE);
                coursePersonnel.setVisibility(View.GONE);
                courseUniversity.setVisibility(View.GONE);
                courseTerm.setVisibility(View.GONE);
                courseTime.setVisibility(View.GONE);
                courseInfo.setVisibility(View.GONE);
                detail.setVisibility(View.VISIBLE);
                detail2.setVisibility(View.GONE);

            }
        });

        Button linkButton = (Button) v.findViewById(R.id.linkButton);
        if (URL.equals("방송")) {
            linkButton.setVisibility(View.GONE);
            courseTime.setVisibility(View.GONE);
            courseDivide.setVisibility(View.GONE);
            coursePersonnel.setVisibility(View.GONE);
        }

        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                parentActivity.startActivity(intent);
            }
        });


        Button addButton = (Button) v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, Main3Activity.class);
                parentActivity.startActivity(intent);
            }

        });




        Button calendarButton = (Button) v.findViewById(R.id.calendarButton);
        calendarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, CalenderActivity.class);
                parentActivity.startActivity(intent);
            }

        });
        return v;
    }
}

