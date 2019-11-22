package com.example.sharedpreferencetest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ActionMenuView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtText;
    //전역변수
    final static String SHARED_NAME="HYE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtText = findViewById(R.id.edtText);
        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_NAME, 0);
        String editValue=sharedPreferences.getString("editText","");
        edtText.setText(editValue);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("editText",edtText.getText().toString().trim());
        editor.commit();


    }
}
