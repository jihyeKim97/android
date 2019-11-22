package com.example.mymediatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private Button btnPlay, btnStop;
    private TextView textView;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;

    //Environment.getExternalStorageDirectory().getPath();
    //핸드폰에 있는 sdCard위치를 나타낸다
    private ArrayList<String> mp3List = new ArrayList<>();
    static final private String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";

    private String seleteMp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);


        //외부 장치를 권한 설정 요청
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        //외부 디렉토리에 있는 파일 및 디렉토리 모든 파일들을 배열로 가져오는 방법
        File[] listFiles = new File(MP3_PATH).listFiles();
        for (File file : listFiles) {
            String fileName = file.getName();//파일 명 또는 다렉토리 명
            String extendName = fileName.substring(fileName.length() - 3);

            if (extendName.equals("mp3") || extendName.equals("mp4")) {
                mp3List.add(fileName);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3List);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        listView.setItemChecked(0, true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seleteMp3 = mp3List.get(position);
            }
        });
        wandooSEttinginit(true, false, View.INVISIBLE);
        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(MP3_PATH + seleteMp3);
                    //외부 파일을 가져오기 위해서 prepar을 진행
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    wandooSEttinginit(false, true, View.VISIBLE);
                    textView.setText("#" + seleteMp3);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnStop:
                mediaPlayer.stop();
                mediaPlayer.reset();
                wandooSEttinginit(true,false,View.INVISIBLE);
                textView.setText("음원을 선택해 주세요 :)");
                break;
        }
    }

    private void wandooSEttinginit(boolean b, boolean b1, int invisible) {
        btnPlay.setClickable(b);
        btnStop.setClickable(b1);
        progressBar.setVisibility(invisible);
    }
}
