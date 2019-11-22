package com.example.user.nugaandoreo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editMessage;
    Button btnShow, btnHome;
    RadioButton rdoCook, rdoFish;
    ImageView IvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("옥땅으로따다와");

        editMessage=findViewById(R.id.editMessage);
        btnShow=findViewById(R.id.btnShow);
        btnHome=findViewById(R.id.btnHome);
        rdoCook=findViewById(R.id.rdoCook);
        rdoFish=findViewById(R.id.rdoFish);
        IvShow=findViewById(R.id.IvShow);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editMessage.getText().toString().trim();
                toastDisplay(message);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editMessage.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(message));
                startActivity(intent);
            }
        });
        rdoCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IvShow.setImageResource(R.drawable.ec);
            }
        });
        rdoFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IvShow.setImageResource(R.drawable.ic);
            }
        });

    }//end of onCreate
    public void toastDisplay(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}
