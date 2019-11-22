package com.example.user.autocompletion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.문자열배열을 만든다
        String [] data = { "CSI-뉴욕", "CSI-라스베가스", "CSI-마이애미",
                "Friends","Fringe", "Lost"};
        autoCompleteTextView  = findViewById(R.id.autoCompleteTextView);
        multiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView);

        //2. 패턴을 인식
        //---소속이 명확할떄 쓰인다
        //객체가 분명할땐, this
        //임시객체 일떈. getAppliecationContext.this
        //객체가 불분명할땐, class.this
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,data);
        MultiAutoCompleteTextView.CommaTokenizer token = new MultiAutoCompleteTextView.CommaTokenizer();
        //두개이상 선택시 콤마별로 멀티요구
        multiAutoCompleteTextView.setTokenizer(token);
        multiAutoCompleteTextView.setAdapter(adapter);
        //자동제공
        autoCompleteTextView.setAdapter(adapter);

    }
}
