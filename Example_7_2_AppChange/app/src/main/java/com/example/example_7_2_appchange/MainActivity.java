package com.example.example_7_2_appchange;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

     LinearLayout LinearLayOut;
     Button btn_contextMenu;
     Button btn_contextMenu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayOut = findViewById(R.id.LinearLayOut);
        btn_contextMenu = findViewById(R.id.btn_contextMenu);
        btn_contextMenu2 = findViewById(R.id.btn_contextMenu2);
        //1.contextmenu는 해당된 위젯을 선택을 해서 등록 해야한다.
        registerForContextMenu(btn_contextMenu);
        registerForContextMenu(btn_contextMenu2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        if(v==btn_contextMenu){
            menuInflater.inflate(R.menu.contextmenu,menu);
        }else if(v==btn_contextMenu2){
            menuInflater.inflate(R.menu.contextmenu,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.contextBlue: LinearLayOut.setBackgroundColor(Color.BLUE);break;
            case R.id.contextGreen: LinearLayOut.setBackgroundColor(Color.GREEN);break;
            case R.id.contextRed: LinearLayOut.setBackgroundColor(Color.RED);break;
        }

        return false;
    }
}
