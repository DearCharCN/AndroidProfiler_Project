package com.dc.androidprofiler;

public class MallInfo {
    public long  nativeHeapSize;
    public long  nativeHeapAlloc;
    public long  nativeHeapFree;

    public MallInfo(long nativeHeapSize, long  nativeHeapAlloc, long  nativeHeapFree) {
        this.nativeHeapSize = nativeHeapSize;
        this.nativeHeapAlloc = nativeHeapAlloc;
        this.nativeHeapFree = nativeHeapFree;
    }
}
