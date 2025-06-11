private void viewImageInStandardApp(final BitmapDrawable image) {
    final File file = LocalStorage.getStorageFile(null, "temp.jpg", false, true);
    try (FileOutputStream fos = new FileOutputStream(file)) {
        image.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, fos);
    } catch (IOException e) {
        Log.e(Settings.tag, "viewImageInStandardApp: " + e.toString());
        return;
    }

    Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setDataAndType(uri, "image/jpeg");
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

    if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
    }

    if (file.exists()) {
        file.deleteOnExit();
    }
}
