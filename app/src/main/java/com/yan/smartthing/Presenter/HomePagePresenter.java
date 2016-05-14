package com.yan.smartthing.Presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.yan.smartthing.Model.BlueToothModel;
import com.yan.smartthing.Model.Environment;
import com.yan.smartthing.Util.StringToWhat;
import com.yan.smartthing.View.HomePage.HomePage;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import cn.bmob.v3.listener.SaveListener;

/**
 * 主页
 * Created by a7501 on 2016/5/12.
 */
public class HomePagePresenter extends MvpBasePresenter<HomePage> {
    private BlueToothModel blueToothModel;
    private Context context;

    private Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.e("HomeFragment", "006" );
            setMessage();
            blueToothModel.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
                @Override
                public void onDataReceived(byte[] data, String message) {
                    Log.e("HomePageRec", "" + message + "\n");
                    Environment environment = StringToWhat.stringToString(message);
                    getView().setEnvironment(environment);
                    environment.save(context, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Log.e("HomeFragment", "saveOk" );
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Log.e("HomeFragment", "saveErr"+s );
                        }
                    });
                }
            });

        }
    };

    public HomePagePresenter(BlueToothModel blueToothModel,Context context) {
        this.blueToothModel = blueToothModel;
        this.context = context;
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
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
                Log.e("HomeFragment", "007" );
            }
        }.start();

    }




}
