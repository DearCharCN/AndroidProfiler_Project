package com.dc.androidprofiler;

public interface BatteryReceiverCallbackForUnity {
    void batteryChanged(int level, int scale, int temperature, int status, int health, int pluggen);
    void batteryLow();
    void batteryOKay();
}
