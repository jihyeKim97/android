package com.example.example_7_signup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText signupEdtId, signupEdtPW, signupEdtName, loginEdtId, loginEdtPW;
    Button btnSignup, btnLogin;
    TextView txt;
    View singupview, loginview, toastview2;
    String[] name = new String[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txt = (TextView) findViewById(R.id.txt);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singupview = View.inflate(MainActivity.this, R.layout.signupview, null);
                final AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("회원가입");
                dlg.setIcon(R.drawable.twitter);
                dlg.setView(singupview);
                final EditText signupEdtId = singupview.findViewById(R.id.signupEdtId);
                final EditText signupEdtPW = singupview.findViewById(R.id.signupEdtPW);
                final EditText signupEdtName = singupview.findViewById(R.id.signupEdtName);

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name[0] = signupEdtId.getText().toString().trim();
                        name[1] = signupEdtPW.getText().toString().trim();
                        name[2] = signupEdtName.getText().toString().trim();
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        toastview2 = View.inflate(MainActivity.this, R.layout.toastview, null);
                        Toast toast = new Toast(MainActivity.this);
                        toast.setView(toastview2);
                        toast.show();
                    }
                });
                dlg.show();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginview = View.inflate(MainActivity.this, R.layout.loginview, null);
                final AlertDialog.Builder dlg1 = new AlertDialog.Builder(MainActivity.this);
                dlg1.setTitle("로그인");
                dlg1.setIcon(R.drawable.twitter);
                dlg1.setView(loginview);
                loginEdtId = loginview.findViewById(R.id.loginEdtId);
                loginEdtPW = loginview.findViewById(R.id.loginEdtPW);

                dlg1.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       if(loginEdtId.getText().toString().equals(name[0])&&loginEdtPW.getText().toString().equals(name[1])){
                           Toast.makeText(getApplicationContext(),"로그인 하셨습니다",Toast.LENGTH_LONG).show();
                       }else{
                           Toast.makeText(getApplicationContext(),"로그인 실패하셨습니다",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                dlg1.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        toastview2 = View.inflate(MainActivity.this, R.layout.toastview, null);
                        Toast toast = new Toast(MainActivity.this);
                        toast.setView(toastview2);
                        toast.show();
                    }
                });
                dlg1.show();
            }
        });

    }
}
