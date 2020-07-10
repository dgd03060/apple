package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MelonActivity extends AppCompatActivity {


    private ListView listView;
    private MelonListAdapter adapter;
    private List<Melon> melonList;
    private List<Melon> saveList;//회원검색 기능용 복사본


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melon);
        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.listview);
        melonList = new ArrayList<Melon>();
        adapter = new MelonListAdapter(getApplicationContext(), melonList,this, saveList);
        listView.setAdapter(adapter);
        saveList = new ArrayList<Melon>();



        try {
            //JSON오브젝트에서 웹데이터를 가져옴
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("melonList"));
            //배열형태로 받아옴
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            //사용자 개수 관리
            int count = 0;
            String courseID; //기사고유번호
            String courseUniversity; // 기사제목
            String courseYear; //가수이름
            String courseTerm; //링크
            String courseArea; //날짜
            String courseMajor; //회사이름
            while(count < jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                courseID = object.getString("courseID");
                courseUniversity = object.getString("courseUniversity");
                courseYear = object.getString("courseYear");
                courseTerm = object.getString("courseTerm");
                courseArea = object.getString("courseArea");
                courseMajor = object.getString("courseMajor");
                Melon course = new Melon(courseID, courseUniversity, courseYear, courseTerm,courseArea,courseMajor);
                melonList.add(course);
                saveList.add(course);//여기도 똑같이 값을 추가해줍니다. 회원검색기능용
                count++;
            }

        }
        //오류가 발생했을 경우 출력
        catch (Exception e) {
            e.printStackTrace();
        }


        EditText search = (EditText)findViewById(R.id.search);

        //EditText에 현재 입력되어잇는 값을 가져온다
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchUser(charSequence.toString());//회원 검색 기능용
            }


            @Override
            public void afterTextChanged(Editable editable) {
            }

        });
    }


    public void searchUser(String search){
        melonList.clear();
        for(int i = 0; i < saveList.size(); i++){
            if(saveList.get(i).getCourseYear().contains(search)){//contains메소드로 search 값이 있으면 true를 반환함
                melonList.add(saveList.get(i));
            }
        }

        adapter.notifyDataSetChanged();//어댑터에 값일 바뀐것을 알려줌
    }
}