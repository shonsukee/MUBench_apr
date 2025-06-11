private void viewImageInStandardApp(final BitmapDrawable image) {
    final File file = LocalStorage.getStorageFile(null, "temp.jpg", false, true);
    try (final FileOutputStream fos = new FileOutputStream(file)) {
        image.getBitmap().compress(CompressFormat.JPEG, 100, fos);
    } catch (Exception e) {
        Log.e(Settings.tag, "cgeoimages.handleMessage.onClick: " + e.toString());
        return;
    }
    final Intent intent = new Intent();
    intent.setAction(android.content.Intent.ACTION_VIEW);
    Uri imageUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
    intent.setDataAndType(imageUri, "image/jpeg");
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    startActivity(intent);
    if (file.exists()) {
        file.deleteOnExit();
    }
}
