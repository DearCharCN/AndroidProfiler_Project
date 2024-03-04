package com.dc.androidprofiler;

import android.os.Build;
import android.os.Debug;

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
}
