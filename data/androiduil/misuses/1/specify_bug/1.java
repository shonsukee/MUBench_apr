public final class StorageUtils {
    public static File getCacheDirectory(Context context, boolean preferExternal) {
        File appCacheDir = null;
        if (preferExternal && Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            appCacheDir = getExternalCacheDir(context);
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

    private static File getExternalCacheDir(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            File appCacheDir = context.getExternalCacheDir();
            if (appCacheDir != null) {
                if (!appCacheDir.exists() && !appCacheDir.mkdirs()) {
                    L.w("Unable to create external cache directory");
                    return null;
                }
                try {
                    File nomedia = new File(appCacheDir, ".nomedia");
                    if (!nomedia.exists()) {
                        nomedia.createNewFile();
                    }
                } catch (IOException e) {
                    L.i("Can't create \".nomedia\" file in application external cache directory");
                }
                return appCacheDir;
            }
        }
        File dataDir = new File(Environment.getExternalStorageDirectory(), "Android/data");
        File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
        if (!appCacheDir.exists() && !appCacheDir.mkdirs()) {
            L.w("Unable to create external cache directory");
            return null;
        }
        try {
            File nomedia = new File(appCacheDir, ".nomedia");
            if (!nomedia.exists()) {
                nomedia.createNewFile();
            }
        } catch (IOException e) {
            L.i("Can't create \".nomedia\" file in application external cache directory");
        }
        return appCacheDir;
    }
}
