package com.example.userinterfacedialog;

import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtName, txtEmail;
    Button btnClick;
    EditText edtName, edtEmail;
    View dialogView, toastView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        btnClick = findViewById(R.id.btnClick);

        btnClick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialogView=View.inflate(MainActivity.this,R.layout.dialog_activity,null);
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("사용자정보입력");
                dialog.setIcon(R.mipmap.twitter);
                dialog.setView(dialogView);
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editName =dialogView.findViewById(R.id.edtName);
                        EditText editEmail =dialogView.findViewById(R.id.edtEmail);
                        txtName.setText(editName.getText().toString().trim());
                        txtEmail.setText(editEmail.getText().toString().trim());
                    }
                });
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        toastView = View.inflate(MainActivity.this, R.layout.toast_activity, null);
                        Toast toast = new Toast(MainActivity.this);
                        toast.setView(toastView);
                        toast.show();

                    }
                });
                dialog.show();
            }
        });
    }
}
