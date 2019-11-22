package com.example.listviewstringtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemLongClickListener {
    private ListView listView;
    private ArrayList<String> arrayData = new ArrayList<String>();
    private Button btnAdd;
    private EditText edtData;
    private  ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        edtData = findViewById(R.id.edtData);

        //4번이 완성(지료 제공 데이터)
        arrayDataInput();

        //5번 어뎁터를 정의 되어있는 어뎁터를 사용하고 방식까지 설정한다.
        //simple_list_item_1.xml은 이미 만들어 져있고 이 안에서 인플렉스를 시킨다(고칠수 없다)
        //arrayData에 하나씩 매치 시킨다.
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_multiple_choice, arrayData);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        btnAdd.setOnClickListener(this);
        listView.setOnItemLongClickListener(this);


    }

    private void arrayDataInput() {
        String[] mid = {"data0", "data1", "data2", "data3", "data4", "data5", "data6", "" +
                "data7", "data8", "data9", "data10", "data11", "data12",
                "data13", "data14", "data15"};
        for (String data : mid) {
            arrayData.add(data);
        }
        return;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView = view.findViewById(android.R.id.text1);
        Log.d("MainActivity", "      i = " + i + "       l = " + l + "    " +
                "  data = " + arrayData.get(i) + "     레알객체 = " + textView.getText().toString());
        Toast.makeText(getApplicationContext(), "i=" + i, Toast.LENGTH_LONG).show();


    }

    @Override
    public void onClick(View v) {
        arrayData.add(edtData.getText().toString().trim());
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "추가한 데이타 = "+edtData.getText().toString(), Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
        String string = arrayData.get(i);
        arrayData.remove(i);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "삭제한 데이타 = "+string, Toast.LENGTH_LONG).show();



        return false;
    }
}
