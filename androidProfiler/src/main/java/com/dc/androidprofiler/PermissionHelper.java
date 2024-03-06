package com.dc.androidprofiler;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

public class PermissionHelper {
    private static final int REQUEST_PERMISSION_READ_EXTERNAL_STORAGE = 1;

    public static void requestReadExternalStoragePermission(Activity context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // 如果没有权限，请求权限
                ActivityCompat.requestPermissions(context,
                        new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
            }
        }
        else {
            if (ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // 如果没有权限，请求权限
                ActivityCompat.requestPermissions( context,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
            }
        }
    }
}
