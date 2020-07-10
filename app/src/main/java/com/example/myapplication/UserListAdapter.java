package com.example.myapplication;
import android.app.Activity;

import android.content.Context;

import android.view.View;

import android.view.ViewGroup;

import android.widget.BaseAdapter;

import android.widget.Button;

import android.widget.TextView;


import com.android.volley.RequestQueue;

import com.android.volley.Response;

import com.android.volley.toolbox.Volley;


import org.json.JSONObject;


import java.util.List;


/**

 * Created by kch on 2018. 2. 17..

 */


public class UserListAdapter extends BaseAdapter {


    private Context context;

    private List<User> userList;

    private Activity parentActivity;//회원삭제 강의때 추가

    private List<User> saveList;


    //여기서 Actvitivy parentActivity가 추가됨 회원삭제 및 관리자기능 예제

    public UserListAdapter(Context context, List<User> userList, Activity parentActivity, List<User> saveList){

        this.context = context;

        this.userList = userList;

        this.parentActivity = parentActivity;//회원삭제 강의때 추가

        this.saveList = saveList;//회원검색 강의때 추가

    }


    //출력할 총갯수를 설정하는 메소드


    @Override

    public int getCount() {

        return userList.size();

    }
    @Override

    public Object getItem(int i) {

        return userList.get(i);

    }
    @Override

    public long getItemId(int i) {
        return i;

    }
    @Override

    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.user, null);


        //뷰에 다음 컴포넌트들을 연결시켜줌

        //final추가 안붙이면 에러남 리스너로 전달하고 싶은 지역변수는 final로 처리해야됨

        final TextView userID = (TextView)v.findViewById(R.id.userID);
        TextView userPassword = (TextView)v.findViewById(R.id.userPassword);
        TextView userGender = (TextView)v.findViewById(R.id.userGender);
        TextView userMajor = (TextView)v.findViewById(R.id.userMajor);


        userID.setText(userList.get(i).getUserID());
        userPassword.setText(userList.get(i).getUserPassword());
        userGender.setText(userList.get(i).getUserGender());
        userMajor.setText(userList.get(i).getUserMajor());


        //이렇게하면 findViewWithTag를 쓸 수 있음 없어도 되는 문장임

        v.setTag(userList.get(i).getUserID());


        //삭제 버튼 객체 생성

        Button deleteButton = (Button)v.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View view) {


                //4. 콜백 처리부분(volley 사용을 위한 ResponseListener 구현 부분)

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override

                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");


                            //받아온 값이 success면 정상적으로 서버로부터 값을 받은 것을 의미함

                            if(success){
                                userList.remove(i);//리스트에서 해당부분을 지워줌


                                //saveList는 찾아서 해줘야됨 이게 기준이기 때문임

                                for(int i = 0; i < saveList.size(); i++){
                                    if(saveList.get(i).getUserID().equals(userID.getText().toString())){
                                        saveList.remove(i);
                                        break;
                                    }
                                }
                                notifyDataSetChanged();//데이터가 변경된 것을 어댑터에 알려줌
                            }
                        }

                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                };
                DeleteRequest deleteRequest = new DeleteRequest(userID.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(parentActivity);
                queue.add(deleteRequest);
            }//onclick

        });
        return v;
    }
}


