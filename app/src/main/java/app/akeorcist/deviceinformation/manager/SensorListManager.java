package app.akeorcist.deviceinformation.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import app.akeorcist.deviceinformation.model.SensorData;

/**
 * Created by Akexorcist on 2/27/15 AD.
 */
public class SensorListManager {
    private static List<Sensor> sensorList;
    private static ArrayList<SensorData> sensorDataList = new ArrayList<>();

    public static void initialData(Activity activity) {
        SensorManager sensorManager = (SensorManager)activity.getSystemService(Context.SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor : sensorList) {
            sensorDataList.add(new SensorData(sensor.getName())
                    .setVendor(sensor.getVendor())
                    .setType(getType(sensor.getType()))
                    .setVersion(sensor.getVersion() + "")
                    .setPower(sensor.getPower() + "")
                    .setMaxRange(sensor.getMaximumRange() + "")
                    .setResolution(sensor.getResolution() + "")
                    .setMinDelay(getMinDelay(sensor))
                    .setMaxDelay(getMaxDelay(sensor))
                    .setFifoMax(getFifoMaxEventCount(sensor))
                    .setFifoReserved(getFifoReservedEventCount(sensor))
                    .setImageResId(getImageResourceId(sensor)));
        }
    }

    @SuppressLint("NewApi")
    private static String getMinDelay(Sensor sensor) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return sensor.getMinDelay() + "";
        }
        return "Unknown";
    }

    @SuppressLint("NewApi")
    private static String getMaxDelay(Sensor sensor) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return sensor.getMaxDelay() + "";
        }
        return "Unknown";
    }

    @SuppressLint("NewApi")
    private static String getFifoReservedEventCount(Sensor sensor) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return sensor.getFifoReservedEventCount() + "";
        }
        return "Unknown";
    }

    @SuppressLint("NewApi")
    private static String getFifoMaxEventCount(Sensor sensor) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return sensor.getFifoMaxEventCount() + "";
        }
        return "Unknown";
    }

    public static SensorData getSensorData(int position) {
        return sensorDataList.get(position);
    }

    public static int getSensorDataCount() {
        return sensorDataList.size();
    }

    @SuppressWarnings("deprecation")
    private static String getType(int type) {
        switch(type) {
            case Sensor.TYPE_ACCELEROMETER:
                return "Accelerometer";
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return "Temperature";
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                return "Game Rotation Vector";
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                return "Geomagnetic Rotation Vector";
            case Sensor.TYPE_GRAVITY:
                return "Gravity";
            case Sensor.TYPE_GYROSCOPE:
                return "Gyroscope";
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                return "Gyroscope Uncalibrated";
            case Sensor.TYPE_HEART_RATE:
                return "Heart Rate";
            case Sensor.TYPE_LIGHT:
                return "Light";
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return "Linear Acceleration";
            case Sensor.TYPE_MAGNETIC_FIELD:
                return "Magnetic Field";
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                return "Magnetic Field Uncalibrated";
            case Sensor.TYPE_ORIENTATION:
                return "Orientation";
            case Sensor.TYPE_PRESSURE:
                return "Pressure";
            case Sensor.TYPE_PROXIMITY:
                return "Proximity";
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return "Humidity";
            case Sensor.TYPE_ROTATION_VECTOR:
                return "Rotation Vector";
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                return "Signigicant Motion";
            case Sensor.TYPE_STEP_COUNTER:
                return "Step Counter";
            case Sensor.TYPE_STEP_DETECTOR:
                return "Step Detector";
            case Sensor.TYPE_TEMPERATURE:
                return "Temperature";
        }
        return "Unknown";
    }

    private static int getImageResourceId(Sensor sensor) {
        //TODO
        return 0;
    }
}