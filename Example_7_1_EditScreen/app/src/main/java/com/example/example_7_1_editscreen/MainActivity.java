package com.example.example_7_1_editscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtText;
    ImageView image1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText = (EditText)findViewById(R.id.edtText);
        image1 = (ImageView)findViewById(R.id.image1);
    }

    //OptionMenu를 사용
    //inflater 사용
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        //객체를 만든 다음에 menu에 적용 시킨다.
        menuInflater.inflate(R.menu.contextmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:image1.setImageResource(R.drawable.bue); break;
            case R.id.item2: image1.setImageResource(R.drawable.ec);break;
            case R.id.item3:image1.setImageResource(R.drawable.eeee); break;
            case R.id.itemRotate:image1.setRotation(image1.getRotation() + Float.parseFloat(edtText.getText().toString())); break;
        }
        return true;
    }
}
