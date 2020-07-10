package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Newspop extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspop);
        WindowManager.LayoutParams layoutParams= new WindowManager.LayoutParams();

        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        layoutParams.dimAmount= 0.7f;getWindow().setAttributes(layoutParams);

        TextView textView = (TextView)findViewById(R.id.content);
        textView.setText("래퍼 로꼬와 배우 스테파니 리가 열애설에 대한 입장을 밝혔다. '이미 결별한 사이' 였다.\n" +
                "\n" +
                "로꼬의 소속사 AOMG와 스테파니 리의 소속사 YNK엔터테인먼트 측은 20일 \"두 사람이 좋은 만남을 가졌던 건 사실이지만 최근 결별했다. 좋은 동료로 남기로 했다\"고 입장을 밝혔다.\n" +
                "\n" +
                "앞서 한 매체는 로꼬와 스테파니 리가 지난해 초 지인의 소개로 만나 1년 넘게 열애를 이어오고 있다고 보도했다. 이 매체는 둘은 지난해 지인의 소개로 만났으며, 다른 사람의 시선을 의식하지 않은 채 만남을 지속해 왔다고 설명했다. 또 지난 2월 로꼬가 군 복무를 시작한 후에도 애정전선에는 변함없다고 전했으나 두 사람은 이미 연인관계를 정리한 상태인 것으로 밝혀졌다.\n" +
                "\n" +
                "2012년 Mnet '쇼미더머니1'에 우승하며 이름을 알린 로꼬는 '시간이 들겠지' '감아' '니가 모르게' '주지마' 등의 곡으로 많은 사랑을 받았다. 지난해 2월 의무경찰로 입대해 군복무 중이다.\n" +
                "\n" +
                "16세 때부터 모델로 연예계 활동을 시작한 스테파니 리는 2014년 JTBC '선암여고 탐정단'을 통해 연기 활동을 시작했다. 드라마 '용팔이', '검법남녀', '황후의 품격', 영화 '신의 한 수 : 귀수편' 등에 출연했다.\n" +
                "\n" +
                "홍신익 기자 hong.shinik@joongang.co.kr\n");




        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.9), (int) (height * 0.85));
    }
}