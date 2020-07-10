package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ManagementActivity extends AppCompatActivity {


    private ListView listView;
    private SearchListAdapter adapter;
    private ListView CourselistView;
    private List<Course> courseList;
    private CourseListAdapter courseadapter;
    private List<Search> searchList;
    private List<Search> saveList;//회원검색 기능용 복사본

    private ArrayAdapter yearAdapter;
    private Spinner yearSpinner;
    private ArrayAdapter termAdapter;
    private Spinner termSpinner;
    private ArrayAdapter areaAdapter;
    private Spinner areaSpinner;
    private ArrayAdapter majorAdapter;
    private Spinner majorSpinner;

    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.listView);
        CourselistView = (ListView) findViewById(R.id.listView2);
        searchList = new ArrayList<Search>();
        courseList = new ArrayList<Course>();
        adapter = new SearchListAdapter(getApplicationContext(), searchList,this, saveList);
        courseadapter = new CourseListAdapter(getApplicationContext(), courseList, this);
        listView.setAdapter(adapter);
        CourselistView.setAdapter(courseadapter);
        saveList = new ArrayList<Search>();
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        termSpinner = (Spinner) findViewById(R.id.termSpinner);
        areaSpinner = (Spinner) findViewById(R.id.areaSpinner);
        majorSpinner = (Spinner) findViewById(R.id.artistSpinner);
        yearAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.month, android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        termAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.place, android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);

        areaAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.universityArea, android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(areaAdapter);



        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(areaSpinner.getSelectedItem().equals("뮤지컬"))
                {
                    majorAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.musicalMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }
                if(areaSpinner.getSelectedItem().equals("연극"))
                {
                    majorAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.theaterMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }
                if(areaSpinner.getSelectedItem().equals("콘서트"))
                {
                    majorAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.concertMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }
                if(areaSpinner.getSelectedItem().equals("무용"))
                {
                    majorAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.muyMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }
                if(areaSpinner.getSelectedItem().equals("클래식/오페라"))
                {
                    majorAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.operaMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }
                if(areaSpinner.getSelectedItem().equals("마술"))
                {
                    majorAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.magicMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }
                if(areaSpinner.getSelectedItem().equals("국악"))
                {
                    majorAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.koreaMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchButton = (Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.GONE);
                new BackgroundTask().execute();
            }
        });


        try {
            //JSON오브젝트에서 웹데이터를 가져옴
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("searchList"));
            //배열형태로 받아옴
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            //사용자 개수 관리
            int count = 0;
            int courseID; //스케줄 고유번호
            String courseUniversity; // 솔로그룹
            int courseYear; //월
            String courseTerm; //장소
            String courseArea; //성별
            String courseMajor; //장르
            String courseGrade; //스케줄날짜
            String courseTitle; //스케줄이름
            String courseCredit; //스케줄시간
            String courseDivide; //티켓예매날짜
            String coursePersonnel; //티켓예매시간
            String courseProfessor; //아티스트이름
            String courseTime; //구매장소
            String courseRoom; //구매링크            //개수를 늘려가며 배열의 모든것을 탐색
            String courseImage;
            while(count < jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                courseID = object.getInt("courseID");
                courseUniversity = object.getString("courseUniversity");
                courseYear = object.getInt("courseYear");
                courseTerm = object.getString("courseTerm");
                courseArea = object.getString("courseArea");
                courseMajor = object.getString("courseMajor");
                courseGrade = object.getString("courseGrade");
                courseTitle = object.getString("courseTitle");
                courseCredit = object.getString("courseCredit");
                courseDivide = object.getString("courseDivide");
                coursePersonnel = object.getString("coursePersonnel");
                courseProfessor = object.getString("courseProfessor");
                courseTime = object.getString("courseTime");
                courseRoom = object.getString("courseRoom");
                courseImage = object.getString("courseImage");
                Search course = new Search(courseID, courseUniversity, courseYear, courseTerm, courseArea, courseMajor, courseGrade, courseTitle, courseCredit, courseDivide, coursePersonnel, courseProfessor, courseTime, courseRoom, courseImage);
                searchList.add(course);
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
        searchList.clear();
        for(int i = 0; i < saveList.size(); i++){
            if(saveList.get(i).getCourseTime().contains(search)){//contains메소드로 search 값이 있으면 true를 반환함
                searchList.add(saveList.get(i));
            }
        }

        adapter.notifyDataSetChanged();//어댑터에 값일 바뀐것을 알려줌
    } class BackgroundTask extends  AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://rbgk60.cafe24.com/CourseList.php?courseUniversity="
                        + "&courseYear=" + URLEncoder.encode(yearSpinner.getSelectedItem().toString().substring(0, 2), "UTF-8")
                        + "&courseTerm=" + URLEncoder.encode(termSpinner.getSelectedItem().toString(), "UTF-8")
                        + "&courseArea=" + URLEncoder.encode(areaSpinner.getSelectedItem().toString(), "UTF-8")
                        + "&courseMajor=" + URLEncoder.encode(majorSpinner.getSelectedItem().toString(), "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super .onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result) {
            try {
                courseList.clear();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;

                int courseID; //스케줄 고유번호
                String courseUniversity; // 솔로그룹
                int courseYear; //월
                String courseTerm; //장소
                String courseArea; //성별
                String courseMajor; //장르
                String courseGrade; //스케줄날짜
                String courseTitle; //스케줄이름
                String courseCredit; //스케줄시간
                String courseDivide; //티켓예매날짜
                String coursePersonnel; //티켓예매시간
                String courseProfessor; //아티스트이름
                String courseTime; //구매장소
                String courseRoom; //구매링크
                String courseImage; //구매링크
                while(count < jsonArray.length())
                {
                    JSONObject object = jsonArray.getJSONObject(count);
                    courseID = object.getInt("courseID");
                    courseUniversity = object.getString("courseUniversity");
                    courseYear = object.getInt("courseYear");
                    courseTerm = object.getString("courseTerm");
                    courseArea = object.getString("courseArea");
                    courseMajor = object.getString("courseMajor");
                    courseGrade = object.getString("courseGrade");
                    courseTitle = object.getString("courseTitle");
                    courseCredit = object.getString("courseCredit");
                    courseDivide = object.getString("courseDivide");
                    coursePersonnel = object.getString("coursePersonnel");
                    courseProfessor = object.getString("courseProfessor");
                    courseTime = object.getString("courseTime");
                    courseRoom = object.getString("courseRoom");
                    courseImage = object.getString("courseImage");
                    Course course = new Course(courseID, courseUniversity, courseYear, courseTerm, courseArea, courseMajor, courseGrade, courseTitle, courseCredit, courseDivide, coursePersonnel, courseProfessor, courseTime, courseRoom, courseImage);
                    courseList.add(course);
                    count++;
                }
                if(count == 0)
                {
                    AlertDialog dialog;
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(ManagementActivity.this);
                    dialog = builder.setMessage("조회된 공연이 없습니다.")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                }
                courseadapter.notifyDataSetChanged();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}