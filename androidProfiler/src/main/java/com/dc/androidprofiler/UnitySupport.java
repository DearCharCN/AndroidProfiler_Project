package com.dc.androidprofiler;

import android.os.Debug;

public class UnitySupport {
    public MemoryInfo getMemonry(){
        MemoryInfo info = new MemoryInfo();
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        info.dalvikHeapSize =  totalMemory;
        info.dalvikHeapAlloc = totalMemory - freeMemory;
        info.dalvikHeapFree = freeMemory;
        System.out.println("one");
        JNI.getMallinfo();
//        MallInfo mInfo = JNI.getMallinfo();
//        info.nativeHeapSize = mInfo.nativeHeapSize;
//        info.nativeHeapAlloc = mInfo.nativeHeapAlloc;
//        info.nativeHeapFree = mInfo.nativeHeapFree;
        System.out.println("two");
        android.os.Debug.MemoryInfo osMInfo = new android.os.Debug.MemoryInfo();
        Debug.getMemoryInfo(osMInfo);
        System.out.println("three");
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
