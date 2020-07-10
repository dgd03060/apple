package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MelonListAdapter extends BaseAdapter{
    private Context context;
    private List<Melon> melonList;
    private Activity parentActivity;
    private List<Melon> saveList;
    private Button linkButton;



    public MelonListAdapter(Context context, List<Melon> melonList, Activity parentActivity, List<Melon> saveList) {
        this.context = context;
        this.melonList = melonList;
        this.parentActivity = parentActivity;
        this.saveList = saveList;//검색
        this.parentActivity = parentActivity;


    }

    @Override
    public int getCount() {
        return melonList.size();
    }

    @Override
    public Object getItem(int position) {
        return melonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override

    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.melon, null);




        //뷰에 다음 컴포넌트들을 연결시켜줌

        //final추가 안붙이면 에러남 리스너로 전달하고 싶은 지역변수는 final로 처리해야됨

        TextView courseID = (TextView) v.findViewById(R.id.text_rank);
        TextView courseUniversity = (TextView) v.findViewById(R.id.text_valance);
        TextView courseYear = (TextView) v.findViewById(R.id.text_name);
        TextView courseTerm = (TextView) v.findViewById(R.id.text_Title);
        TextView courseHeart = (TextView)v.findViewById(R.id.courseHeart);
        final ImageButton start = (ImageButton) v.findViewById(R.id.start);
        final ImageButton stop = (ImageButton)v.findViewById(R.id.stop);

        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];

        final ImageView courseImage = (ImageView)v.findViewById(R.id.img_flag);



        final String imageUrl = melonList.get(i).getCourseArea();
        Glide.with(parentActivity).load(imageUrl).into(courseImage);

        courseID.setText(melonList.get(i).getCourseID().substring(0, 2));
        courseUniversity.setText(melonList.get(i).getCourseUniversity());
        courseYear.setText(melonList.get(i).getCourseYear());
        courseTerm.setText(melonList.get(i).getCourseTerm());
        courseHeart.setText(melonList.get(i).getCourseMajor());

        stop.setVisibility(View.GONE);



        //재생버튼 눌렀을때..
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer[0] = MediaPlayer.create(parentActivity, R.raw.aloah);
                mediaPlayer[0].start();
                start.setVisibility(View.GONE);
                stop.setVisibility(View.VISIBLE);
            }
        });

        //정지버튼
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer[0].isPlaying()) {
                    mediaPlayer[0].stop();
                    mediaPlayer[0].reset();
                    start.setVisibility(View.VISIBLE);
                    stop.setVisibility(View.GONE);
                }
            }
        });










        return v;
    }
}

