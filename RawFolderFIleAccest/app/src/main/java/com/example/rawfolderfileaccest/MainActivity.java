package com.example.rawfolderfileaccest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRead;
    EditText edtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = (Button)findViewById(R.id.btnRead);
        edtText = (EditText)findViewById(R.id.edtText);
        //1.버튼 이벤트
        btnRead.setOnClickListener(this);
    }

    //1.버튼 이벤트
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRead : rawReadFile();break;
        }
    }

    public void rawReadFile() {
        InputStream is =getResources().openRawResource(R.raw.raw_test);
        try {
            byte[] txt = new byte[is.available()];
            is.read(txt);
            edtText.setText(new String(txt));
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
