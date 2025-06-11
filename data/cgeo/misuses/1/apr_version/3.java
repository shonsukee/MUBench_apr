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

        final Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "image/jpg");
        startActivity(intent);

        if (file.exists()) {
            file.deleteOnExit();
        }
    }
