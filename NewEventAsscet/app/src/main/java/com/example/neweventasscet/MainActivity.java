package com.example.neweventasscet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnOption,btnList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnList = (Button)findViewById(R.id.btnList);
        btnOption = (Button)findViewById(R.id.btnOption);

        btnList.setOnClickListener(this);
        btnOption.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnList:
                ArrayList<String> list=new ArrayList<String>();
                list.add("정민지혜");
                list.add("심재혀니");
                list.add("서살몬");
                list.add("김하느리");
                list.add("안소영");
                list.add("갱상도성민");
                final String[] items = list.toArray(new String[list.size()]);
              /*  final String[] items = new String[list.size()];
                int size=0;
                for(String data:list){
                    items[size++]=data;
                }*/

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("목록상자");
                dialog.setItems(items,null);
                dialog.show();

                break;
            case R.id.btnOption:
                showDialogOption();
                break;
        }

    }

    private void showDialogOption() {
        ArrayList<String> list=new ArrayList<String>();
        list.add("정민지혜");
        list.add("심재혀니");
        list.add("서살몬");
        list.add("김하느리");
        list.add("안소영");
        list.add("갱상도성민");
        final String[] items = new String[list.size()];
        int size=0;
        for(String data:list){
            items[size++]=data;
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("목록상자");
        dialog.setSingleChoiceItems(items, -1 , null);
        dialog.show();


    }
}
