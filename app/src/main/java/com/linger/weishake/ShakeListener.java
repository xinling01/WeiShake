package com.linger.weishake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by linger on 2018/10/22.
 */

public class ShakeListener implements SensorEventListener {
    //传感器管理
    private SensorManager sensorManager;
    //传感器
    Sensor sensor;
    //上下文
    private Context mContext;
    //传感器的监听器
    private OnShakeListener onShakeListener;

    //获得加速度传感器感应变化的数据
    public ShakeListener(Context c) {
        mContext = c;
        start();
    }

    //加速度传感器开始
    public void start() {
        //获得传感器服务
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            //获得加速度传感器
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    //停止传感器
    public void stop() {
        sensorManager.unregisterListener(this);
    }

    //摇晃监听接口
    public interface OnShakeListener {
        public void onShake();
    }

    //设置传感器监听
    public void setOnShakeListener(OnShakeListener listenr) {
        onShakeListener = listenr;
    }

    //获得加速度传感器感应变化的数据
    @Override
    public void onSensorChanged(SensorEvent event) {
        //获取x、y、三个值
        float x=event.values[0];
        float y=event.values[1];
        float z=event.values[2];
        //当三轴的数值大于12
        if(x>12||y>12||z>12){
            onShakeListener.onShake();
        }
    }

    //当传感器的精度发生改变时被调用
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
