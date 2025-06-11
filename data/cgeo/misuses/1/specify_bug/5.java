private void viewImageInStandardApp(final BitmapDrawable image) {
    File file = LocalStorage.getStorageFile(null, "temp.jpg", false, true);
    try (FileOutputStream fos = new FileOutputStream(file)) {
        image.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, fos);
    } catch (IOException e) {
        Log.e(Settings.TAG, "Error writing temp image", e);
        return;
    }

    Uri uri = FileProvider.getUriForFile(
        this,
        BuildConfig.APPLICATION_ID + ".fileprovider",
        file
    );

    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setDataAndType(uri, "image/jpeg");
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

    if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
    }

    if (file.exists()) {
        file.delete();
    }
}
