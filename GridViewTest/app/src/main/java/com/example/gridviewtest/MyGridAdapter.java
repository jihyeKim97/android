package com.example.gridviewtest;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyGridAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<MyData> list;
    private LayoutInflater layoutInflater;

    public MyGridAdapter(Context context, int layout, ArrayList<MyData> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    //얼마나 만들것이냐
    @Override
    public int getCount() {
        return list.size();
    }
    //안에 구성할 내용은 무엇이냐
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    //위치를 나타냄
    @Override
    public long getItemId(int position) {
        return position;
    }

    //뿌려주는 작업
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(layout, null);
        }
        final ImageView ivAdapter = convertView.findViewById(R.id.ivAdapter);
        MyData myData = list.get(position);
        ivAdapter.setImageResource(myData.getPosterId());
        ivAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(context, R.layout.dialog, null);
                ImageView imageView = view.findViewById(R.id.imageView);
                MyData bigImg = list.get(position);
                imageView.setImageResource(bigImg.getPosterId());
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("포스터 상세보기");
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setView(view);
                dialog.setPositiveButton("닫기", null);
                dialog.show();
            }
        });
        return convertView;
    }
}