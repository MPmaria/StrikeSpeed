package com.mp.user.strikespeed.logics;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.SystemClock;

import com.mp.user.strikespeed.R;
import com.mp.user.strikespeed.ResultActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.mp.user.strikespeed.MainActivity.WAITING_TIME;

public class Calculator implements SensorEventListener {

    private long timeBeforeStart;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private MediaPlayer mediaPlayer;

    private double acceleration = 0;
    private double maxAcceleration = 0;

    private long reactionTime;
    private long startTime;
    private long timeInterval;

    private double xAcceleration = 0;
    private double yAcceleration = 0;
    private double zAcceleration = 0;

    private double xDirection = 0;
    private double yDirection = 0;
    private double zDirection = 0;

    private double prevXDirection = 0;
    private double prevYDirection = 0;
    private double prevZDirection = 0;

    private double speed = 0;
    private static final double DELTA = 0.018;

    public Calculator(Context context) {
        sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null){
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }

        mediaPlayer = MediaPlayer.create(context, R.raw.bang);
        timeBeforeStart = generateTimeInterval();
        reactionTime = WAITING_TIME;
        timeInterval = WAITING_TIME;

        new CountDownTimer(timeBeforeStart, 100) {
            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                startTime = System.currentTimeMillis();
                mediaPlayer.start();
            }
        }.start();
    }

    private long generateTimeInterval(){
        long min = 500;
        long max = 1500;
        Random r = new Random();
        long number = min+((long)(r.nextDouble()*(max-min)));
        return number;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        xAcceleration = Math.abs(event.values[0]);
        yAcceleration = Math.abs(event.values[1]);
        zAcceleration = Math.abs(event.values[2]);

        xDirection = Math.signum(zAcceleration);
        yDirection = Math.signum(yDirection);
        zDirection = Math.signum(zDirection);

        acceleration = Math.sqrt(xAcceleration*xAcceleration+yAcceleration*yAcceleration+zAcceleration*zAcceleration);

        if(acceleration <= 2) {
            acceleration = 0;
        }

        else if(acceleration > 2){
            timeInterval = System.currentTimeMillis() - startTime;
        }

        if(!changedDirection(prevXDirection, xDirection, prevYDirection, yDirection, prevZDirection, zDirection)) {
            calculateMaxAcceleration();
            findReactionTime();
            calculateSpeed(acceleration);
        }


    }

    private void findReactionTime(){
        if(timeInterval <  reactionTime){
            reactionTime = timeInterval;
        }
    }

    private void calculateMaxAcceleration(){
        if(acceleration > maxAcceleration){
            maxAcceleration = acceleration;
        }
    }

    private void calculateSpeed(double acceleration){
        speed = speed + acceleration*DELTA;
    }


    public double getMaxAcceleration() {
        return maxAcceleration;
    }

    public  double getReactionTime(){
        return  reactionTime;
    }

    public double getSpeed(){
        return speed;
    }
    public void setNoValue(){
        acceleration = 0;
    }

    private boolean changedDirection(double xPrev, double x, double yPrev, double y, double zPrev, double z){

        if((xPrev + x == 0)&&(yPrev + y == 0)&&(zPrev + z == 0)){
            return true;
        }
        return false;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
