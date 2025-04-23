public final class StorageUtils {
    public static File getCacheDirectory(Context context, boolean preferExternal) {
        File appCacheDir = null;
        if (preferExternal) {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                appCacheDir = getExternalCacheDir(context);
            }
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        if (appCacheDir == null) {
            String cacheDirPath = context.getApplicationInfo().dataDir + "/cache/";
            L.w("Can't define system cache directory! '%s' will be used.", cacheDirPath);
            appCacheDir = new File(cacheDirPath);
        }
        return appCacheDir;
    }

    private static File getExternalCacheDir(Context context) {
        File appCacheDir = context.getExternalCacheDir();
        if (appCacheDir == null) {
            return null;
        }
        if (!appCacheDir.exists() && !appCacheDir.mkdirs()) {
            L.w("Unable to create external cache directory");
            return null;
        }
        try {
            File noMedia = new File(appCacheDir, ".nomedia");
            if (!noMedia.exists()) {
                noMedia.createNewFile();
            }
        } catch (IOException e) {
            L.i("Can't create \".nomedia\" file in application external cache directory", e);
        }
        return appCacheDir;
    }
}
