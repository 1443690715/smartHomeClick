package com.yan.smartthing.Presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.yan.smartthing.Model.BlueToothModel;
import com.yan.smartthing.View.HomePage.HomePage;

/**
 * 主页
 * Created by a7501 on 2016/5/12.
 */
public class HomePagePresenter extends MvpBasePresenter<HomePage> {
    private BlueToothModel blueToothModel;

    private Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.e("HomeFragment", "006" );
            setMessage();



        }
    };

    public HomePagePresenter(BlueToothModel blueToothModel) {
        this.blueToothModel = blueToothModel;
    }

    public boolean isBlueToothModelIsNull(){
        return blueToothModel == null;
    }

    public void setMessage(){
        getView().setMyDriverState("111");
    }

    /**
     * 监测设备是否在线
     */
    public void chickDrives(){

        new Thread(){
            @Override
            public void run() {
                Log.e("HomeFragment", "005" +getView().BlueToothIsOnline() );
                while(!getView().BlueToothIsOnline()){}
                handler.sendEmptyMessage(0);
                Log.e("HomeFragment", "007" );
            }
        }.start();

    }




}
