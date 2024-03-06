package com.dc.androidprofiler;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;

public class BatteryReceiver extends BroadcastReceiver {

    protected BatteryReceiverCallbackForUnity callbackIns;
    protected Activity ownerActivity;
    public void setCallback(BatteryReceiverCallbackForUnity cb){
        callbackIns = cb;
    }

    public void setOwnerActivity(Activity activity){
        ownerActivity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int temperature =  intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            int status  = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            int pluggen = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
            int capacity = -1;//电池容量，单位微安时
            int current = -1;//平均电流，微安


            BatteryManager batteryManager = null;
            if (ownerActivity != null){
                batteryManager = (BatteryManager)ownerActivity.getSystemService(Context.BATTERY_SERVICE);
            }
            if (batteryManager != null){

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    capacity = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
                    current = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE);
                }
            }

            System.out.println("Battery Changed: level = "+level+"; scale = "+scale+"; temperature = " +
                    temperature + "; status = " + status + "; health = " + health + "; pluggen = " + pluggen + ";");
            if (callbackIns != null){
                callbackIns.batteryChanged(level, scale, temperature, status, health, pluggen,voltage,capacity,current);
            }
        }
        else if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction())){
            if (callbackIns != null){
                callbackIns.batteryLow();
            }
        }
        else if (Intent.ACTION_BATTERY_OKAY.equals(intent.getAction())){
            if (callbackIns != null){
                callbackIns.batteryOKay();
            }
        }
    }
}
