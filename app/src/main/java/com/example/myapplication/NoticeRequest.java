package com.example.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 김규하 2019.11.24. ...
 */

public class NoticeRequest extends StringRequest {

    final static private String URL = "http://rbgk60.cafe24.com/NoticeRequest.php";
    private Map<String, String> parameters;

    public NoticeRequest(String noticeTitle, String noticeContent, String noticeName, String noticeDate, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("noticeTitle", noticeTitle);
        parameters.put("noticeContent", noticeContent);
        parameters.put("noticeName", noticeName);
        parameters.put("noticeDate", noticeDate);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}


