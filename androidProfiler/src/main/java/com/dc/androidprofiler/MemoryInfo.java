package com.dc.androidprofiler;

import java.util.ArrayList;
import java.util.Map;

public class MemoryInfo
{
    //Java Runtime API
    public long dalvikHeapSize;
    public long dalvikHeapAlloc;
    public long dalvikHeapFree;

    //c++层获取的数据
    public long  nativeHeapSize;
    public long  nativeHeapAlloc;
    public long  nativeHeapFree;

    //Java Debug API获取的数据
    public int dalvikPss;
    public int dalvikPrivateDirty;
    public int dalvikSharedDirty;

    public int nativePss;
    public int nativePrivateDirty;
    public int nativeSharedDirty;

    public int otherPss;
    public int otherPrivateDirty;
    public int otherSharedDirty;
    public ArrayList<String> statusList;
}