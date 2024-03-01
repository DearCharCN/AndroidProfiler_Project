package com.dc.androidprofiler;

public class JNI {
    static {
        System.loadLibrary("Memory");
    }
    public native String sayHello();

    public static native MallInfo getMallinfo();
}
