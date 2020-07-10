package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NoticeListAdapter extends BaseAdapter{
    private Context context;
    private Activity parentActivity;
    private List<Notice> noticedList;

    public NoticeListAdapter(Context context, List<Notice> noticedList, Activity parentActivity) {
        this.context = context;
        this.noticedList = noticedList;
        this.parentActivity = parentActivity;
    }

    @Override
    public int getCount() {
        return noticedList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticedList.get(position);//해당 위치의 값을 리스트뷰에 뿌려줌
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View v = View.inflate(context, R.layout.notice, null);


        TextView nameText = (TextView)v.findViewById(R.id.nameText);
        TextView dateText = (TextView)v.findViewById(R.id.dateText);
        TextView noticeText = (TextView)v.findViewById(R.id.noticeText);
        TextView contentText = (TextView)v.findViewById(R.id.contentText);

        noticeText.setText(" " + noticedList.get(position).getNotice());
        nameText.setText(noticedList.get(position).getName());
        dateText.setText(noticedList.get(position).getDate());
        contentText.setText(noticedList.get(position).getContent());

        contentText.setVisibility(View.GONE);

        v.setTag(noticedList.get(position).getNotice());
        v.setTag(noticedList.get(position).getContent());
        v.setTag(noticedList.get(position).getDate());

        final String title = noticedList.get(position).getNotice();
        final String content = noticedList.get(position).getContent();
        final String date = noticedList.get(position).getDate();
        final String name = noticedList.get(position).getName();


        noticeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, NoticePop.class);
                intent.putExtra("title", title);
                intent.putExtra("date", date);
                intent.putExtra("name", name);
                intent.putExtra("content", content);
                parentActivity.startActivity(intent);
            }
        });
        return v;
    }
}
