package com.dc.androidprofiler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class BatteryReceiver extends BroadcastReceiver {

    protected BatteryReceiverCallbackForUnity callbackIns;

    public void setCallback(BatteryReceiverCallbackForUnity cb){
        callbackIns = cb;
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

            System.out.println("Battery Changed: level = "+level+"; scale = "+scale+"; temperature = " +
                    temperature + "; status = " + status + "; health = " + health + "; pluggen = " + pluggen + ";");
            if (callbackIns != null){
                callbackIns.batteryChanged(level, scale, temperature, status, health, pluggen);
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
