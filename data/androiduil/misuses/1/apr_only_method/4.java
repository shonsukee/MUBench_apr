/**
 * Returns application cache directory. Cache directory will be created on SD card
 * <i>("/Android/data/[app_package_name]/cache")</i> (if card is mounted and app has appropriate permission) or
 * on device's file system depending incoming parameters.
 *
 * @param context        Application context
 * @param preferExternal Whether prefer external location for cache
 * @return Cache {@link File directory}.<br />
 * <b>NOTE:</b> Can be null in some unpredictable cases (if SD card is unmounted and
 * {@link android.content.Context#getCacheDir() Context.getCacheDir()} returns null).
 */
public static File getCacheDirectory(Context context, boolean preferExternal) {
    File appCacheDir = null;
    if (preferExternal && MEDIA_MOUNTED
            .equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
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
