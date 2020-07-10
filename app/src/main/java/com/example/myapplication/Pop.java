package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class Pop extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop);
        WindowManager.LayoutParams layoutParams= new WindowManager.LayoutParams();

        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        layoutParams.dimAmount= 0.7f;getWindow().setAttributes(layoutParams);




        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.9), (int) (height * 0.55));
    }
}