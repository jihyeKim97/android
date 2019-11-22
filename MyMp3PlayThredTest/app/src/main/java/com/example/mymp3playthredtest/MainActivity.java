package com.example.mymp3playthredtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mymp3playthredtest.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listViewMP3;
    private SeekBar seekBar;
    private TextView textViewPlayingState, textViewPlayingMusicName;
    private Button btPlay, btPause;

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private ArrayList<String> list = new ArrayList<>();
    private String selectedMP3;

    public static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMP3 = findViewById(R.id.listViewMP3);
        seekBar = findViewById(R.id.seekBar);
        textViewPlayingState = findViewById(R.id.textViewPlayingState);
        textViewPlayingMusicName = findViewById(R.id.textViewPlayingMusicName);
        btPlay = findViewById(R.id.btPlay);
        btPause = findViewById(R.id.btPause);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        getFileList();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(adapter);
        listViewMP3.setItemChecked(0, true);

        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMP3 = list.get(position);
            }
        });

        btPlay.setOnClickListener(this);
        btPause.setOnClickListener(this);

        buttonSetting(true, false, 0);

    }

    private void buttonSetting(boolean b, boolean b1, int progress) {
        btPlay.setClickable(b);
        btPause.setClickable(b1);
        seekBar.setProgress(progress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btPlay:
                try {
                    mediaPlayer.setDataSource(MP3_PATH + selectedMP3);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    buttonSetting(false, true, 0);
                    textViewPlayingMusicName.setText("실행중인 음악 : " + selectedMP3);

                    Thread thread = new Thread() {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {

                            if (mediaPlayer == null) {
                                return;
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textViewPlayingState.setText(selectedMP3 + "재생 시간 : " + mediaPlayer.getDuration());
                                    seekBar.setMax(mediaPlayer.getDuration());
                                }
                            });
                            while (mediaPlayer.isPlaying()) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                                        textViewPlayingState.setText("진행 시간 " + simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                                    }
                                });
                                SystemClock.sleep(2000);
                            }
                        }
                    };

                    thread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btPause:
                mediaPlayer.stop();
                mediaPlayer.reset();
                btPlay.setClickable(true);
                btPause.setClickable(false);
                textViewPlayingMusicName.setText("실행중인 음악 : ");
                textViewPlayingState.setText("진행시간 : ");
                seekBar.setProgress(0);
                break;
        }
    }

    private void getFileList() {

        File[] fileList = new File(MP3_PATH).listFiles();

        for (File file : fileList) {
            String fileName = file.getName();
            String extendSting = fileName.substring(fileName.length() - 3);

            if (extendSting.equals("mp3") || extendSting.equals("mp4")) {
                list.add(fileName);
            }
        }
    }

}