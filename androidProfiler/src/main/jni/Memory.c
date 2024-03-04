#include <stdio.h>
#include <stdlib.h>
#include <jni.h>

jobject JNICALL
Java_com_dc_androidprofiler_JNI_getMallinfo(JNIEnv *env, jobject thiz) {
    struct mallinfo info = mallinfo();
    jlong  nativeHeapSize = (jlong)info.usmblks;
    jlong  nativeHeapAlloc = (jlong)info.uordblks;
    jlong  nativeHeapFree = (jlong)info.fordblks;
    jclass mallInfoClass = (*env)->FindClass(env, "com/dc/androidprofiler/MallInfo");
    jmethodID constructor = (*env)->GetMethodID(env, mallInfoClass, "<init>", "(JJJ)V");
    jobject mallInfoObj = (*env)->NewObject(env, mallInfoClass, constructor,nativeHeapSize, nativeHeapAlloc, nativeHeapFree);
    return mallInfoObj;
}