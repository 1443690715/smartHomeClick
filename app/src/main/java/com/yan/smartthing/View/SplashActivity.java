package com.yan.smartthing.View;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yan.smartthing.Model.BlueToothModel;
import com.yan.smartthing.R;
import com.yan.smartthing.View.LoginView.LoginActivity;

import app.akexorcist.bluetotohspp.library.BluetoothState;

public class SplashActivity extends AppCompatActivity {

    private BlueToothModel blueToothModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_acitvity);

        blueToothModel = BlueToothModel.getInstance(getApplicationContext());
        if(!blueToothModel.isBluetoothAvailable()) {
            Toast.makeText(this,"蓝牙设备不支持",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(blueToothModel.isBluetoothEnabled()) {
            Toast.makeText(this,"蓝牙已打开",Toast.LENGTH_SHORT).show();
            if(!blueToothModel.isServiceAvailable()) {
                blueToothModel.setupService();
                blueToothModel.startService(BluetoothState.DEVICE_OTHER);
            }

        } else {
            Toast.makeText(this,"请打开蓝牙",Toast.LENGTH_SHORT).show();
        }





        new Thread(){
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(3000);
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }.start();


    }


}
