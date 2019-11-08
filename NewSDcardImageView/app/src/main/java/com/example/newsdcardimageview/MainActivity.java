package com.example.newsdcardimageview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btPrevious, btNext;
    MyPictureView myPictureView;
    File[] imageFiles;
    int currentPoint = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        btPrevious=findViewById(R.id.btPrevious);
        btNext=findViewById(R.id.btNext);
        myPictureView=findViewById(R.id.myPictureView);

        //맨 처음 화면을 보여줘야 한다.

        imageFiles=new File(Environment.getExternalStorageDirectory().
                getAbsolutePath()+"/pic").listFiles();

        myPictureView.setSrc(imageFiles[currentPoint].toString().trim());
        myPictureView.invalidate();

        btPrevious.setOnClickListener(this);
        btNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btPrevious: imageChangePrevious();break;
            case R.id.btNext: imageChangeNext(); break;
        }
    }


    private void imageChangePrevious() {
        currentPoint -=1;
        currentPoint = (currentPoint<0)?(4):(currentPoint);
        myPictureView.setSrc(imageFiles[currentPoint].toString());
        //이미지 소스가 변경되었다. 즉시 다시 그려라. = invalidate
        myPictureView.invalidate();
    }
    private void imageChangeNext() {
        currentPoint +=1;
        currentPoint = (currentPoint>4)?(0):(currentPoint);
        myPictureView.setSrc(imageFiles[currentPoint].toString());
        //이미지 소스가 변경되었다. 즉시 다시 그려라. = invalidate
        myPictureView.invalidate();
    }

}
