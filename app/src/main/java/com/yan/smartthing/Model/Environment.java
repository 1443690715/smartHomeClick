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
    private Float pm2;             //pm2.5
    private Integer waterXian;      //水位限度

    public Integer getWaterXian() {
        return waterXian;
    }

    public void setWaterXian(Integer waterXian) {
        this.waterXian = waterXian;
    }

    public Float getPm2() {
        return pm2;
    }

    public void setPm2(Float pm2) {
        this.pm2 = pm2;
    }

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
