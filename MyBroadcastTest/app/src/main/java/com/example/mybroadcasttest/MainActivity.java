package com.example.mybroadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewBattery;
    private EditText editText;

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewBattery = findViewById(R.id.imageViewBattery);
        editText = findViewById(R.id.editText);

        callBroadCastReceiver();

    }

    @Override
    protected void onResume() {
        super.onResume();
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    private void callBroadCastReceiver() {

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if(action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                    int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                    editText.setText("Now Battery is "+ remain + "\n");

                    if(remain >= 90) {
                        imageViewBattery.setImageResource(R.mipmap.battery_100);
                    }else if (remain >= 70) {
                        imageViewBattery.setImageResource(R.mipmap.battery_80);
                    }else if (remain >= 50) {
                        imageViewBattery.setImageResource(R.mipmap.battery_60);
                    }else if (remain >= 10) {
                        imageViewBattery.setImageResource(R.mipmap.battery_20);
                    }else {
                        imageViewBattery.setImageResource(R.mipmap.battery_0);
                    }

                    int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                    switch (plug) {
                        case 0:
                            editText.append("Not Connecting power");
                            break;
                        case BatteryManager.BATTERY_PLUGGED_AC :
                            editText.append("Adapter Connecting");
                            break;
                        case BatteryManager.BATTERY_PLUGGED_USB :
                            editText.append("USB Connecting");
                            break;
                    }
                }
            }
        };
    }
}