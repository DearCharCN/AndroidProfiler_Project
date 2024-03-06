package com.dc.androidprofiler;

public interface BatteryReceiverCallbackForUnity {
    void batteryChanged(int level, int scale, int temperature, int status, int health, int pluggen,int voltage,int capacity,int current);
    void batteryLow();
    void batteryOKay();
}
