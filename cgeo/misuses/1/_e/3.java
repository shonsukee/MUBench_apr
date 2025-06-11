import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.content.Intent;
import android.util.Log;

private void viewImageInStandardApp(final BitmapDrawable image) {
    final File file = LocalStorage.getStorageFile(null, "temp.jpg", false, true);
    try (FileOutputStream fos = new FileOutputStream(file)) {
        image.getBitmap().compress(CompressFormat.JPEG, 100, fos);
    } catch (IOException e) {
        Log.e(Settings.tag, "cgeoimages.handleMessage.onClick: " + e, e);
        return;
    }

    Uri uri = FileProvider.getUriForFile(this, "com.example.myapp.fileprovider", file);
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setDataAndType(uri, "image/jpeg");
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    startActivity(intent);

    if (file.exists()) {
        file.deleteOnExit();
    }
}
