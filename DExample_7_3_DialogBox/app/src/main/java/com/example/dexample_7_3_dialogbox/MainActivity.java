package com.example.dexample_7_3_dialogbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn = (Button) findViewById(R.id.btn);
        final TextView txt = (TextView) findViewById(R.id.txt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] versionArray  = new String[] {"Oreo","Pie","Nuga"};
                AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);
                dig.setTitle("좋아하는 버전은?");
                dig.setIcon(R.mipmap.twitter);
                dig.setItems(versionArray , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn.setText(versionArray[which]);
                        txt.setText(versionArray[which]);
                    }
                });
                dig.setPositiveButton("Close", null);
                dig.show();
            }
        });
    }
}
