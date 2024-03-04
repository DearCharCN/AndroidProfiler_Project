package com.dc.androidprofiler;

public class JNI {
    static {
        System.loadLibrary("Memory");
    }
    public static native MallInfo getMallinfo();
}