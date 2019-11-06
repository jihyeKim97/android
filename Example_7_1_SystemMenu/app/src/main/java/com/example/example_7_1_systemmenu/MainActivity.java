package com.example.example_7_1_systemmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button btnMenu;
    LinearLayout LinearLayOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu = findViewById(R.id.btnMenu);
        LinearLayOut = findViewById(R.id.LinearLayOut);

    }
    //1.시스템 옵션메뉴 선택
    //xml - 1.class<BUtton>메모리에 임플렉션
    //메뉴를 임플레이트 하고 옵션 메뉴등록한다.
    //Inflater = 인스턴스
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*super.onOptionsItemSelected(item);*/
        switch (item.getItemId()){
            //이벤트 등록
            case R.id.itemBlue : LinearLayOut.setBackgroundColor(Color.BLUE);break;
            case R.id.itemGreen :  LinearLayOut.setBackgroundColor(Color.GREEN);break;
            case R.id.itemRed : LinearLayOut.setBackgroundColor(Color.RED);break;
            case R.id.sub : btnMenu.setRotation(45f); break;
            case R.id.big : btnMenu.setScaleX(2);break;
            default:break;
        }


        return true;
    }
}
