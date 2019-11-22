package com.example.imagevotetest;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv[] = new TextView[9];
    Integer tvID[] = {R.id.textView01, R.id.textView02, R.id.textView03, R.id.textView04, R.id.textView05,
            R.id.textView06, R.id.textView07, R.id.textView08, R.id.textView09};
    RatingBar rBar[] = new RatingBar[9];
    Integer rBarID[] = {R.id.ratingBar01, R.id.ratingBar02, R.id.ratingBar03, R.id.ratingBar04, R.id.ratingBar05,
            R.id.ratingBar06, R.id.ratingBar07, R.id.ratingBar08, R.id.ratingBar09};
    Button btClose;
    int count[] = new int[9];
    String ivName[] = new String[9];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        count = intent.getIntArrayExtra("count");
        ivName = intent.getStringArrayExtra("imageNameList");

        btClose= findViewById(R.id.btClose);
        btClose.setOnClickListener(this);

        for (int i = 0; i < tvID.length; i++) {
            tv[i] = findViewById(tvID[i]);
            rBar[i] = findViewById(rBarID[i]);
            tv[i].setText(ivName[i]);
            rBar[i].setRating(count[i]);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btClose){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("message","전부 종료 되었습니다.");
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
