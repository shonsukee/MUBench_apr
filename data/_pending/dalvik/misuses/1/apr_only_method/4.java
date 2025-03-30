private void viewImageInStandardApp(final BitmapDrawable image) {
    final File file = LocalStorage.getStorageFile(null, "temp.jpg", false, true);
    try {
        final FileOutputStream fos = new FileOutputStream(file);
        image.getBitmap().compress(CompressFormat.JPEG, 100, fos);
        fos.close();
    } catch (Exception e) {
        Log.e(Settings.tag, "cgeoimages.handleMessage.onClick: " + e.toString());
        return;
    }
    Uri uri = FileProvider.getUriForFile(this, "com.example.myapp.fileprovider", file);
    final Intent intent = new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    intent.setDataAndType(uri, "image/jpeg");
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    startActivity(intent);
    if (file.exists()) {
        file.deleteOnExit();
    }
}
