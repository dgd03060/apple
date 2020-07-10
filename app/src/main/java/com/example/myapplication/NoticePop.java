package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class NoticePop extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticepop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams layoutParams= new WindowManager.LayoutParams();

        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        layoutParams.dimAmount= 0.7f;getWindow().setAttributes(layoutParams);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.85), (int) (height * 0.6));

        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        String content = intent.getExtras().getString("content");
        String date = intent.getExtras().getString("date");
        String name = intent.getExtras().getString("name");

        TextView title2 = (TextView)findViewById(R.id.title);
        TextView name2 = (TextView)findViewById(R.id.gm);
        TextView content2 = (TextView)findViewById(R.id.content);
        TextView date2 = (TextView)findViewById(R.id.time);


        title2.setText(title);
        content2.setText(content);
        date2.setText(date);
        name2.setText(name);



    }
}