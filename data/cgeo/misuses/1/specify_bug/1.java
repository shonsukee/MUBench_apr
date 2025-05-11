private void viewImageInStandardApp(final BitmapDrawable image) {
    final File file = LocalStorage.getStorageFile(null, "temp.jpg", false, true);
    try (FileOutputStream fos = new FileOutputStream(file)) {
        image.getBitmap().compress(CompressFormat.JPEG, 100, fos);
    } catch (IOException e) {
        Log.e(Settings.tag, "cgeoimages.handleMessage.onClick: " + e.toString());
        return;
    }

    Uri uri;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);
    } else {
        uri = Uri.fromFile(file);
    }

    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setDataAndType(uri, "image/jpeg");
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    try {
        startActivity(intent);
    } catch (ActivityNotFoundException e) {
        Log.e(Settings.tag, "No application found to view image: " + e.toString());
    }

    if (file.exists()) {
        file.delete();
    }
}
