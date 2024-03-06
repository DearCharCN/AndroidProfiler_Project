package com.dc.androidprofiler;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Debug;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class UnitySupport {
    public MemoryInfo getMemonry(){
        MemoryInfo info = new MemoryInfo();
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        info.dalvikHeapSize =  totalMemory;
        info.dalvikHeapAlloc = totalMemory - freeMemory;
        info.dalvikHeapFree = freeMemory;
        MallInfo mInfo = JNI.getMallinfo();
        info.nativeHeapSize = mInfo.nativeHeapSize;
        info.nativeHeapAlloc = mInfo.nativeHeapAlloc;
        info.nativeHeapFree = mInfo.nativeHeapFree;
        Debug.MemoryInfo osMInfo = new Debug.MemoryInfo();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Debug.getMemoryInfo(osMInfo);
            Map<String, String> statsMap = osMInfo.getMemoryStats();
            ArrayList<String> list = new  ArrayList<String>();
            for (Map.Entry<String, String> entry : statsMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                list.add(key);
                list.add(value);
            }
            info.statusList = list;
        }
        else {
            System.out.println("curSdkVersion: " + Build.VERSION.SDK_INT);
        }
        Debug.getMemoryInfo(osMInfo);
        info.dalvikPss = osMInfo.dalvikPss;
        info.dalvikPrivateDirty = osMInfo.dalvikPrivateDirty;
        info.dalvikSharedDirty = osMInfo.dalvikSharedDirty;

        info.nativePss = osMInfo.nativePss;
        info.nativePrivateDirty = osMInfo.nativePrivateDirty;
        info.nativeSharedDirty = osMInfo.nativeSharedDirty;

        info.otherPss = osMInfo.otherPss;
        info.otherPrivateDirty = osMInfo.otherPrivateDirty;
        info.otherSharedDirty = osMInfo.otherSharedDirty;
        return  info;
    }


    CPUInfoFrame lastFrame;
    public CPUInfoInternal readCpuInfo(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/proc/stat"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("cpu ")) {
                    String[] fields = line.trim().split("\\s+");
                    long totalCPUTime = 0;
                    for (int i = 1; i < fields.length; i++) {
                        totalCPUTime += Long.parseLong(fields[i]);
                    }
                    long idleCPUTime = Long.parseLong(fields[3]);

                    CPUInfoFrame cpuInfo = new CPUInfoFrame();
                    cpuInfo.set(totalCPUTime,totalCPUTime - idleCPUTime);

                    CPUInfoInternal result = null;
                    if (lastFrame != null){
                        result = CPUInfoInternal.getInternal(lastFrame, cpuInfo);
                    }
                    lastFrame = cpuInfo;
                    return result;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("has error");
            e.printStackTrace();
        }
        return null;


    }


    private BatteryReceiver batteryReceiver;
    private Activity registerBatteryReceiverActivity;
    //注册电量消息
    public boolean registerBatteryReceiver(Activity activity, BatteryReceiverCallbackForUnity unityCallback){
        if (activity == null)
            return false;

        if (batteryReceiver != null || registerBatteryReceiverActivity != null){
            return false;
        }

        batteryReceiver = new BatteryReceiver();
        batteryReceiver.setCallback(unityCallback);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        activity.registerReceiver(batteryReceiver, filter);
        registerBatteryReceiverActivity = activity;
        return true;
    }
    //注销电量消息
    public boolean unregisterBatteryReceiver(){
        if (batteryReceiver == null || registerBatteryReceiverActivity == null){
            return false;
        }

        registerBatteryReceiverActivity.unregisterReceiver(batteryReceiver);
        registerBatteryReceiverActivity = null;
        batteryReceiver = null;
        return true;
    }

}
