package com.example.imagevotetest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.imagevotetest.R;
import com.example.imagevotetest.ResultActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView[] imageViewList = new ImageView[9];
    private Integer[] idList = null;
    private Button btVoteCheck;
    private String[] imageNameList = {"1번 그림", "2번 그림", "3번 그림", "4번 그림", "5번 그림", "6번 그림", "7번 그림", "8번 그림", "9번 그림"};
    private int count[] = new int[9];

    final static int REQUESTCODE = 1000, RESULTCODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btVoteCheck = findViewById(R.id.btVoteCheck);
        btVoteCheck.setOnClickListener(this);

        idList = new Integer[]{R.id.image01, R.id.image02, R.id.image03, R.id.image04, R.id.image05,
                R.id.image06, R.id.image07, R.id.image08, R.id.image09};

        for(int i = 0; i < imageNameList.length; i++) {

            imageViewList[i] = findViewById(idList[i]);
            imageViewList[i].setOnClickListener(this);

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.image01 :
                imageVoteChecked(0);
                break;
            case R.id.image02 :
                imageVoteChecked(1);
                break;
            case R.id.image03 :
                imageVoteChecked(2);
                break;
            case R.id.image04 :
                imageVoteChecked(3);
                break;
            case R.id.image05 :
                imageVoteChecked(4);
                break;
            case R.id.image06 :
                imageVoteChecked(5);
                break;
            case R.id.image07 :
                imageVoteChecked(6);
                break;
            case R.id.image08 :
                imageVoteChecked(7);
                break;
            case R.id.image09 :
                imageVoteChecked(8);
                break;
            case R.id.btVoteCheck :
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("imageNameList",imageNameList);
                intent.putExtra("count", count);

                startActivityForResult(intent, REQUESTCODE);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            String message = data.getStringExtra("message");
            toastDisplay("투표 결과는 초기화 됩니다.");
            for(int i = 0; i < count.length; i++) {
                count[i] = 0;
            }
        }
    }

    private void toastDisplay(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void imageVoteChecked(int num) {
        count[num] = (count[num] == 5) ? 5 : ++count[num];
        if(count[num] == 5) {
            toastDisplay("투표 가능 횟수를 초과했습니다.");
        } else {
            toastDisplay(imageNameList[num] + "번째 그림에 투표가 반영 되었습니다.");
        }
    }

}