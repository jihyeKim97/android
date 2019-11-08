package com.example.sam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button btnFileList;
    EditText edtFileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]
                {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        btnFileList = findViewById(R.id.btnFileList);
        edtFileList = findViewById(R.id.edtFileList);

        btnFileList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getRootDirectory().getAbsolutePath();
                File[] dirFile = (new File(path+"/android5ban")).listFiles();
                String strFileName = "";
                for(int i = 0;i<dirFile.length;i++){
                if(dirFile[i].isDirectory()==true){
                    strFileName = "<폴더>"+dirFile[i].toString();
                }else{
                    strFileName = "<파일>"+dirFile[i].toString();
                }
                edtFileList.setText(edtFileList.getText()+"\n"+strFileName);
                }
            }
        });

    }
}
