package com.example.mp3palyertest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
private Switch switchMusic;
private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchMusic = findViewById(R.id.switchMusic);
        //음악을 들을 곡을 선택한 미디어 플레이어 객체를 만듬
        MediaPlayer.create(this,R.raw.love_poem);
        switchMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchMusic.isChecked()){
                    mediaPlayer.start();
                }else{
                    mediaPlayer.stop();
                }
            }
        });
    }
}
