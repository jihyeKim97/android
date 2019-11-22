package com.example.example_12_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtPersonnel, edtNameResult, edtNumberResult;
    private Button btnSetting, btnInput, btnEdit, btnDelete, btnLookup;
    SQLiteDatabase sqlDB;
    myDBHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtPersonnel = findViewById(R.id.edtPersonnel);
        edtNameResult = findViewById(R.id.edtNameResult);
        edtNumberResult = findViewById(R.id.edtNumberResult);

        btnSetting = findViewById(R.id.btnSetting);
        btnInput = findViewById(R.id.btnInput);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnLookup = findViewById(R.id.btnLookup);

        myHelper = new myDBHelper(this);

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES('" + edtName.getText().toString() + "'," + edtPersonnel.getText().toString() + ");");
                sqlDB.close();
                toastDisplay("Input is complete");
                btnLookup.callOnClick();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                if (edtName.getText().toString() != "") {
                    if(!isExist(edtName.getText().toString())){
                        toastDisplay("그룹명이 존재 하지 않습니다");
                    }else {
                        sqlDB.execSQL("UPDATE groupTBL SET gNumber =" + edtPersonnel.getText().toString() + " WHERE gName ='" + edtName.getText().toString() + "';");
                        toastDisplay("Edit is complete");
                    }
                }

                sqlDB.close();

                btnLookup.callOnClick();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                if (edtName.getText().toString() != "") {
                    sqlDB.execSQL("DELETE FROM groupTBL WHERE gName ='" + edtName.getText().toString() + "';");
            }
                sqlDB.close();
                toastDisplay("Deletion completed");
                btnLookup.callOnClick();
            }
        });
        btnLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL ORDER BY gNumber asc/*asc=오름차순,desc=내림차순*/;", null);

                String strNames = "그룹이름" + "\r\n" + "--------" + "\r\n";
                String strNumbers = "인원" + "\r\n" + "--------" + "\r\n";

                while (cursor.moveToNext()) {
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }
                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);
                cursor.close();
                sqlDB.close();

            }
        });
    }

    private boolean isExist(String gName) {
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM groupTBL WHERE gName = '"+gName+"';", null);
        if(cursor.getCount()==0){
            cursor.close();
            return false;
        }else {
            cursor.close();
            return true;
        }

    }

    private void toastDisplay(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
