private void viewImageInStandardApp(final BitmapDrawable image) {
    final File file = LocalStorage.getStorageFile(null, "temp.jpg", false, true);
    try (FileOutputStream fos = new FileOutputStream(file)) {
        image.getBitmap().compress(CompressFormat.JPEG, 100, fos);
    } catch (IOException e) {
        Log.e(Settings.tag, "cgeoimages.handleMessage.onClick: " + e.toString());
        return;
    }

    Intent intent = new Intent(Intent.ACTION_VIEW);
    Uri uri;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        String authority = getPackageName() + ".fileprovider";
        uri = FileProvider.getUriForFile(this, authority, file);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    } else {
        uri = Uri.fromFile(file);
    }
    intent.setDataAndType(uri, "image/jpeg");
    startActivity(intent);

    if (file.exists()) {
        file.deleteOnExit();
    }
}
