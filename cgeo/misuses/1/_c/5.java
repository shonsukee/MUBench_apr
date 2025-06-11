private void viewImageInStandardApp(final BitmapDrawable image) {
    final File file = LocalStorage.getStorageFile(null, "temp.jpg", false, true);
    try (FileOutputStream fos = new FileOutputStream(file)) {
        image.getBitmap().compress(CompressFormat.JPEG, 100, fos);
    } catch (IOException e) {
        Log.e(Settings.tag, "cgeoimages.handleMessage.onClick: " + e.toString());
        return;
    }

    final Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setDataAndType(Uri.fromFile(file), "image/jpeg");
    startActivity(intent);

    if (file.exists()) {
        file.delete();
    }
}
