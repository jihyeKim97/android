package com.example.example_mp3_player;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listViewMP3;
    private SeekBar seekBar;
    private TextView textViewPlayingState, textViewPlayingMusicName;
    private ImageButton btPlay, btPause, btMyList;

    public static SQLiteDatabase sqlDB;
    public static myDBHelper myDBHelper;

    private MyAdapter adapter;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private ArrayList<MyData> list = new ArrayList<>();
    private String selectedMP3;

    public static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("[ # MY MP3 PLAYER ]");

        listViewMP3 = findViewById(R.id.listViewMP3);
        seekBar = findViewById(R.id.seekBar);
        textViewPlayingState = findViewById(R.id.textViewPlayingState);
        textViewPlayingMusicName = findViewById(R.id.textViewPlayingMusicName);
        btPlay = findViewById(R.id.btPlay);
        btPause = findViewById(R.id.btPause);
        btMyList = findViewById(R.id.btMyList);

        myDBHelper = new myDBHelper(this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        getFileList();

        MyAdapter adapter = new MyAdapter(list, R.layout.listview_item, MainActivity.this);
        listViewMP3.setAdapter(adapter);
        listViewMP3.setItemChecked(0, true);
        listViewMP3.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {



                sqlDB = myDBHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL values();");
                sqlDB.close();
                toastDisplay("Your wish list contains the songs you selected");
                return false;
            }
        });//end of OnItemLongClickListener

        listViewMP3.setOnItemClickListener(this);
        btPlay.setOnClickListener(this);
        btPause.setOnClickListener(this);
        btMyList.setOnClickListener(this);

        buttonSetting(true, false, 0);



    }


    //-------------------------------------------------------------------------------------------------------------------------------------

    //FileList
    private void getFileList() {

        File[] fileList = new File(MP3_PATH).listFiles();

        for (File file : fileList) {
            String fileName = file.getName();
            String extendSting = fileName.substring(fileName.length() - 3);

            if (extendSting.equals("mp3") || extendSting.equals("mp4")) {

                list.add(new MyData(fileName,null));
            }
        }
    }//end of FileList

    //-------------------------------------------------------------------------------------------------------------------------------------

    //buttonSetting
    private void buttonSetting(boolean b, boolean b1, int progress) {
        btPlay.setClickable(b);
        btPause.setClickable(b1);
        seekBar.setProgress(progress);
    }//end of buttonSetting

    //-------------------------------------------------------------------------------------------------------------------------------------

    //onClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btPlay:
                buttonPlayAction();
                break;
            case R.id.btPause:
                buttonPauseAction();
                break;
            case R.id.btMyList:
                buttonMyListAction();
                break;
        }
    }//end of onClick

    //-------------------------------------------------------------------------------------------------------------------------------------

    //btPlay Button EventAction
    private void buttonPlayAction() {
        try {
            mediaPlayer.setDataSource(MP3_PATH + selectedMP3);
            mediaPlayer.prepare();
            mediaPlayer.start();

            buttonSetting(false, true, 0);
            textViewPlayingMusicName.setText("RUNNING MUSIC -  " + selectedMP3);

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
                            textViewPlayingState.setText(selectedMP3 + mediaPlayer.getDuration());
                            seekBar.setMax(mediaPlayer.getDuration());
                        }
                    });
                    while (mediaPlayer.isPlaying()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                                textViewPlayingState.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                            }
                        });
                        SystemClock.sleep(1500);
                    }
                }
            };

            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of buttonPlayAction

    //------------------------------------------------------------------------------------------------------------------------------------

    //btPause Button EventAction
    private void buttonPauseAction() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        btPlay.setClickable(true);
        btPause.setClickable(false);
        textViewPlayingMusicName.setText("Running Music -  ");
        seekBar.setProgress(0);
        toastDisplay("현재 음악을 중지 하였습니다.");
    }//end of buttonPauseAction

    //-------------------------------------------------------------------------------------------------------------------------------------

    //btMyList Button EventAction
    private void buttonMyListAction() {
        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        startActivity(intent);

    }//end of buttonMyListAction

    // -------------------------------------------------------------------------------------------------------------------------------------

    //Toast message
    private void toastDisplay(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }//end of Toast message


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedMP3 = list.get(position).getAblumId();
    }
}
