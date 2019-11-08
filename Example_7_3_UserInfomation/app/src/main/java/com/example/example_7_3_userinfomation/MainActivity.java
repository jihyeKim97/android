package com.example.example_7_3_userinfomation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtName, edtEmail, edtNameInput, edtEmailInput;
    Button btnClick;
    View dialog, toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName =findViewById(R.id.edtName);
        edtEmail =findViewById(R.id.edtEmail);
        btnClick =findViewById(R.id.btnClick);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = View.inflate(MainActivity.this, R.layout.dialog, null);

                final AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.twitter);
                dlg.setView(dialog);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int which) {
                        EditText edtName1 = dialog.findViewById(R.id.edtNameInput);
                        EditText edtEmail1 = dialog.findViewById(R.id.edtEmailInput);
                        edtName.setText(edtName1.getText().toString().trim());
                        edtEmail.setText(edtEmail1.getText().toString().trim());
                    }
                });

                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        toast = View.inflate(MainActivity.this, R.layout.toast, null);
                        Toast to = new Toast(MainActivity.this);
                        to.setView(toast);
                        to.show();
                    }
                });
                dlg.show();
            }
        });
    }
}
