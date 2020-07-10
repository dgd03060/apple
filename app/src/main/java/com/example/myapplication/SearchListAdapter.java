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

public class SearchListAdapter extends BaseAdapter{
    private Context context;
    private List<Search> searchList;
    private Activity parentActivity;
    private List<Search> saveList;
    private Button linkButton;



    public SearchListAdapter(Context context, List<Search> searchList, Activity parentActivity, List<Search> saveList) {
        this.context = context;
        this.searchList = searchList;
        this.parentActivity = parentActivity;
        this.saveList = saveList;//회원검색 강의때 추가

    }

    @Override
    public int getCount() {
        return searchList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override

    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.search, null);


        //뷰에 다음 컴포넌트들을 연결시켜줌

        //final추가 안붙이면 에러남 리스너로 전달하고 싶은 지역변수는 final로 처리해야됨

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

        final String imageUrl = searchList.get(i).getCourseImage();
        Glide.with(parentActivity).load(imageUrl).into(courseImage);


        courseGrade.setText("날짜 : " + searchList.get(i).getCourseGrade());
        courseTitle.setText(searchList.get(i).getCourseTitle());
        courseCredit.setText("시간 : " + searchList.get(i).getCourseCredit());
        courseDivide.setText("날짜 : " + searchList.get(i).getCourseDivide());
        coursePersonnel.setText("시간 : " + searchList.get(i).getCoursePersonnel());
        courseProfessor.setText(searchList.get(i).getCourseProfessor());
        courseUniversity.setText(searchList.get(i).getCourseUniversity());
        courseTerm.setText(searchList.get(i).getCourseTerm());
        courseMj.setText(searchList.get(i).getCourseArea() + " >");
        courseMj2.setText(searchList.get(i).getCourseMajor());

        courseGrade.setVisibility(View.GONE);
        courseCredit.setVisibility(View.GONE);
        courseDivide.setVisibility(View.GONE);
        coursePersonnel.setVisibility(View.GONE);
        courseUniversity.setVisibility(View.GONE);
        courseTerm.setVisibility(View.GONE);
        courseTime.setVisibility(View.GONE);
        courseInfo.setVisibility(View.GONE);
        detail2.setVisibility(View.GONE);

        final String URL = searchList.get(i).getCourseRoom();


        v.setTag(searchList.get(i).getCourseID());

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

