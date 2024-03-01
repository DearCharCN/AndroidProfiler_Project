#include <stdio.h>
#include <stdlib.h>
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_dc_safearea_JNI_sayHello(JNIEnv *env, jobject thiz) {
    printf("1");
}

jobject JNICALL
Java_com_dc_safearea_JNI_getMallinfo(JNIEnv *env, jobject thiz) {
printf("1.1");
struct mallinfo info = mallinfo();
long  nativeHeapSize =info.usmblks;
long  nativeHeapAlloc =info.uordblks;
long  nativeHeapFree =info.fordblks;
printf("1.2");
jclass mallInfoClass = (*env)->FindClass(env, "com/dc/memoryprofiler/MallInfo");
printf("1.3");
jmethodID constructor = (*env)->GetMethodID(env, mallInfoClass, "<init>", "(IIIIII)V");
printf("1.4");
jobject mallInfoObj = (*env)->NewObject(env, mallInfoClass, constructor,nativeHeapSize, nativeHeapAlloc, nativeHeapFree);
printf("1.5");
return mallInfoObj;
}
