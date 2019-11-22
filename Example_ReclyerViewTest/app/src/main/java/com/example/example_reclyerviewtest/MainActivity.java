package com.example.example_reclyerviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_reclyerviewtest.R;
import com.example.example_reclyerviewtest.myDBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private EditText edtName, edtPersonnel;
    private Button btnSetting, btnInput, btnEdit, btnDelete, btnLookup;

    public static SQLiteDatabase sqlDB;
    public static myDBHelper myDBHelper;

    private MyAdapter adapter;
    private ArrayList<MyData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        edtName = findViewById(R.id.edtName);
        edtPersonnel = findViewById(R.id.edtPersonnel);

        btnSetting = findViewById(R.id.btnSetting);
        btnInput = findViewById(R.id.btnInput);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnLookup = findViewById(R.id.btnLookup);

        btnSetting.setOnClickListener(this);
        btnInput.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnLookup.setOnClickListener(this);
        myDBHelper = new myDBHelper(this);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                sqlDB = myDBHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM groupTBL WHERE gName ='" + list.get(position).getName() + "';");
                sqlDB.close();

                buttonLookupAction();
                return false;
            }
        });

    }

    private void toastDisplay(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSetting:
                buttonSettingAction();
                break;
            case R.id.btnInput:
                buttonInputAction();
                break;
            case R.id.btnEdit:
                buttonEditAction();
                break;
            case R.id.btnDelete:
                buttonDeleteAction();
                break;
            case R.id.btnLookup:
                buttonLookupAction();
                break;
        }
    }

    private void buttonLookupAction() {
        sqlDB = myDBHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM groupTBL ORDER BY gNumber asc/*asc=오름차순,desc=내림차순*/;", null);

        String strName = "";
        String strNumber = "";
        list.removeAll(list);
        while (cursor.moveToNext()) {
            strName = cursor.getString(0);
            strNumber = cursor.getString(1);
            list.add(new MyData(strName, strNumber));
        }

        adapter = new MyAdapter(list, R.layout.listview, MainActivity.this);
        listView.setAdapter(adapter);

    }

    private void buttonDeleteAction() {
        sqlDB = myDBHelper.getWritableDatabase();
        if (edtName.getText().toString() != "") {
            sqlDB.execSQL("DELETE FROM groupTBL WHERE gName ='" + edtName.getText().toString() + "';");
        }
        sqlDB.close();
        toastDisplay("Deletion completed");
        buttonLookupAction();
    }

    private void buttonEditAction() {
        sqlDB = myDBHelper.getReadableDatabase();
        if (edtName.getText().toString() != "") {
            if (!isExist(edtName.getText().toString())) {
                toastDisplay("존재 하지 않습니다");
            } else {
                sqlDB.execSQL("UPDATE groupTBL SET gNumber =" + edtPersonnel.getText().toString() + " WHERE gName ='" + edtName.getText().toString() + "';");
                toastDisplay("Edit is complete");
            }
        }
        sqlDB.close();
        buttonLookupAction();
    }

    private void buttonInputAction() {
        sqlDB = myDBHelper.getWritableDatabase();
        sqlDB.execSQL("INSERT INTO groupTBL VALUES('" + edtName.getText().toString() + "'," + edtPersonnel.getText().toString() + ");");
        sqlDB.close();
        toastDisplay("Input is complete");
        buttonLookupAction();
    }

    private void buttonSettingAction() {
        sqlDB = myDBHelper.getWritableDatabase();
        myDBHelper.onUpgrade(sqlDB, 1, 2);
        sqlDB.close();
        buttonLookupAction();
    }

    private boolean isExist(String gName) {
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM groupTBL WHERE gName = '" + gName + "';", null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }

    }
}
