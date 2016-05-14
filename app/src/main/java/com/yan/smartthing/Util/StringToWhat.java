package com.yan.smartthing.Util;

import com.yan.smartthing.Model.Environment;

/**
 * Created by a7501 on 2016/1/24.
 */
public class StringToWhat {

    private static float value;

    public static float stringToFloat(String s){
        float lastValue = 0;
        try {
            value = Float.valueOf(s);
            lastValue = value;
        }catch (Exception e){
            return lastValue;
        }

        return value;
    }

    public static Environment stringToString(String s){
        Environment stringNum = new Environment();

        try {
            String num[] = s.split(":");
            stringNum.setTemperature(stringToFloat(num[0]));
            stringNum.setHumidity(stringToFloat(num[1]));
            stringNum.setWater(stringToFloat(num[2]));

        }catch (Exception e){
            stringNum.setTemperature((float)0);
            stringNum.setHumidity((float)0);
            stringNum.setWater((float)0);
        }

        return stringNum;
    }
}
