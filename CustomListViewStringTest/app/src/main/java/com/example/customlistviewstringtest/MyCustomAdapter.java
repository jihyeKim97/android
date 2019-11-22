package com.example.customlistviewstringtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter {

    //this를 저장할 멤버 변수가 필요함
    private Context context;
    //객체의 주소를 받음
    private int layOut;
    private ArrayList<MyCustomDAO> list;
    //일반 클래스에서 스스로가 인플렉션을 진행 할려면 context에게 요청을 해야한다.
    private LayoutInflater layoutInflater;

    public MyCustomAdapter(Context context, int layOut, ArrayList<MyCustomDAO> list) {
        this.context = context;
        this.layOut = layOut;
        this.list = list;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //데이타 자료의 갯수를 리턴
    //추상화 메소드
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

    //
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //객체가 되었음 =  setContentView(R.layout.activity_main);
        if(view==null) {
            view = layoutInflater.inflate(layOut, null);
        }
        final TextView textView = view.findViewById(R.id.textView);
        MyCustomDAO myCustomDAO=list.get(position);
        textView.setText(myCustomDAO.getStringData());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,textView.getText().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
