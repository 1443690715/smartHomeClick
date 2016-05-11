package com.yan.smartthing.Model;

import android.content.Context;
import android.util.Log;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

/**
 * 蓝牙  对象单例化
 * Created by a7501 on 2016/5/11.
 */


/***
 * private volatile static Singleton singleton;
 private Singleton (){}
 public static Singleton getSingleton() {
 if (singleton == null) {
 synchronized (Singleton.class) {
 if (singleton == null) {
 singleton = new Singleton();
 }
 }
 }
 return singleton;
 }
 */
public class BlueToothModel extends BluetoothSPP{

    private volatile static BlueToothModel blueToothModel;
    public static BlueToothModel getInstance(Context context){
        if (blueToothModel == null){
            synchronized (BlueToothModel.class){
                if (blueToothModel == null){
                    blueToothModel = new BlueToothModel(context);
                }
            }
        }
        Log.e("blueModel",""+blueToothModel.toString());
        return blueToothModel;

    }
    private BlueToothModel(Context context){
        super(context);

    }
}
