public static File getCacheDirectory(Context context, boolean preferExternal) {
    File appCacheDir = null;
    if (preferExternal && Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
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
