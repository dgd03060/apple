package com.example.myapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class NoticeActivity extends AppCompatActivity {
    private EditText title, name, date, content;
    private Dialog dialog;
    private Button noticebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        title = findViewById(R.id.et_title);
        name = findViewById(R.id.et_Name);
        date = findViewById(R.id.et_date);
        content = findViewById(R.id.et_content);

        //공지등록을 눌렀을 경우
        noticebutton = findViewById(R.id.noticeButton);
        noticebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noticeTitle = title.getText().toString();
                String noticeContent = content.getText().toString();
                String noticeName = name.getText().toString();
                String noticeDate = date.getText().toString();


                //한칸이라도 빠뜨렸을 경우
                if(noticeTitle.equals("")||noticeContent.equals("")||noticeName.equals("")||noticeDate.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(NoticeActivity.this);
                    dialog = builder.setMessage("빈칸을 입력하세요.")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                //공지등록 시작
                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){//공지등록성공시
                                AlertDialog.Builder builder = new AlertDialog.Builder(NoticeActivity.this);
                                dialog = builder.setMessage("공지등록 완료")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                finish();//액티비티를 종료시킴(회원등록 창을 닫음)
                            }else{//공지실패시
                                AlertDialog.Builder builder = new AlertDialog.Builder(NoticeActivity.this);
                                dialog = builder.setMessage("공지등록 실패")
                                        .setNegativeButton("OK", null)
                                        .create();
                                dialog.show();
                            }

                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };//Response.Listener 완료

                NoticeRequest noticeRequest = new NoticeRequest(noticeTitle, noticeContent, noticeName, noticeDate, responseListener);
                RequestQueue queue = Volley.newRequestQueue(NoticeActivity.this);
                queue.add(noticeRequest);
            }
        });
    }
}

