package com.yan.smartthing.Model;

import cn.bmob.v3.BmobObject;

/**
 * 环境参数
 * Created by a7501 on 2016/5/14.
 */
public class Environment extends BmobObject{
    private Float temperature;   //温度
    private Float humidity;      //湿度
    private Float water;           //水位


    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public Float getWater() {
        return water;
    }

    public void setWater(Float water) {
        this.water = water;
    }


    @Override
    public String toString() {
        return "Environment{" +
                "temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", water='" + water + '\'' +
                '}';
    }
}
