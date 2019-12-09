package com.example.androidproject_ver.Calender.Calender_LIstView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidproject_ver.R;

import java.util.ArrayList;

public class MyList_Adapter extends BaseAdapter {
    // 레이아웃의 아이디를 가지고 인플레이터를 이용해서 객체만듬
    private LayoutInflater inflater;
    //데이터
    private ArrayList<MyList_Data> list;
    // 레이아웃의 아이디
    private int layoutID;
    //어뎁터가 어디서 사용될지 알아야 한다
    private Context context;

    public MyList_Adapter(ArrayList<MyList_Data> list, int layoutID, Context context) {
        this.list = list;
        this.layoutID = layoutID;
        this.context = context;
        this.inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layoutID, null);
        }

        ImageView listImgView = convertView.findViewById(R.id.listImgView);
        TextView listTextViewRdo = convertView.findViewById(R.id.listTextViewRdo);
        TextView listTextViewCombo = convertView.findViewById(R.id.listTextViewCombo);
        TextView listTextViewEdt = convertView.findViewById(R.id.listTextViewEdt);

        if(listTextViewRdo.getText().toString().equals("수입")){
            listImgView.setImageResource(R.mipmap.income);
        }else if(listTextViewRdo.getText().toString().equals("지출")){
            listImgView.setImageResource(R.mipmap.expenditure);
        }

        listTextViewRdo.setText(list.get(position).getCoinrdo());
        listTextViewCombo.setText(list.get(position).getCoinfilter());
        listTextViewEdt.setText(list.get(position).getCoinedttxt());

        listTextViewCombo.setTag(position);


        return convertView;
    }
}
