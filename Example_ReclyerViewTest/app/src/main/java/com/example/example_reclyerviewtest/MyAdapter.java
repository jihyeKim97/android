package com.example.example_reclyerviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.view.menu.MenuView;

import java.util.ArrayList;

import static com.example.example_reclyerviewtest.MainActivity.sqlDB;

public class MyAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<MyData> list;
    // 레이아웃의 아이디
    private int layoutID;
    private Context context;

    public MyAdapter(ArrayList<MyData> list, int layoutID, Context context) {
        this.list = list;
        this.layoutID = layoutID;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //객체를 만들어서 진짜 만드는것
        if (convertView == null) {
            convertView = inflater.inflate(layoutID, null);
        }
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtPersonnel = convertView.findViewById(R.id.txtPersonnel);

        txtName.setText(list.get(position).getName());
        txtPersonnel.setText(list.get(position).getNumber());

        txtName.setTag(position);
        txtName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });
        return convertView;
    }
}
