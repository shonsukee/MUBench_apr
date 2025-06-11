/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.nostra13.universalimageloader.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

public final class StorageUtils {

    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String INDIVIDUAL_DIR_NAME = "uil-images";

    private StorageUtils() {
    }

    public static File getCacheDirectory(Context context) {
        return getCacheDirectory(context, true);
    }

    public static File getCacheDirectory(Context context, boolean preferExternal) {
        File appCacheDir = null;
        if (preferExternal && hasExternalStoragePermission(context) && isExternalStorageMounted()) {
            appCacheDir = context.getExternalCacheDir();
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        if (appCacheDir == null) {
            String cacheDirPath = "/data/data/" + context.getPackageName() + "/cache/";
            L.w("Can't define system cache directory! '%s' will be used.", cacheDirPath);
            appCacheDir = new File(cacheDirPath);
        }
        return appCacheDir;
    }

    public static File getIndividualCacheDirectory(Context context) {
        File cacheDir = getCacheDirectory(context);
        File individualCacheDir = new File(cacheDir, INDIVIDUAL_DIR_NAME);
        if (!individualCacheDir.exists() && !individualCacheDir.mkdir()) {
            individualCacheDir = cacheDir;
        }
        return individualCacheDir;
    }

    public static File getOwnCacheDirectory(Context context, String cacheDir) {
        File appCacheDir = null;
        if (hasExternalStoragePermission(context) && isExternalStorageMounted()) {
            File externalDir = new File(Environment.getExternalStorageDirectory(), cacheDir);
            if (externalDir.exists() || externalDir.mkdirs()) {
                appCacheDir = externalDir;
            }
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        return appCacheDir;
    }

    private static boolean isExternalStorageMounted() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private static boolean hasExternalStoragePermission(Context context) {
        return context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION)
                == PackageManager.PERMISSION_GRANTED;
    }
}
