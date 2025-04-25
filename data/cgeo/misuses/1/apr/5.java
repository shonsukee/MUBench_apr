package cgeo.geocaching;

import cgeo.geocaching.activity.AbstractActivity;
import cgeo.geocaching.files.LocalStorage;
import cgeo.geocaching.network.HtmlImage;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class cgeoimages extends AbstractActivity {

    private static final int MENU_BROWSER = 2;
    private static final int MENU_FILE = 1;
    private static final int UNKNOWN_TYPE = 0;
    private static final int LOG_IMAGES = 1;
    private static final int SPOILER_IMAGES = 2;

    private String geocode = null;
    private LayoutInflater inflater = null;
    private ProgressBar progressBar = null;
    private LinearLayout imagesView = null;
    private int count = 0;
    private int countDone = 0;
    private final HashMap<Integer, cgImage> images = new HashMap<>();
    private BitmapDrawable currentDrawable;
    private cgImage currentImage;

    static private final Collection<Bitmap> bitmaps = Collections.synchronizedCollection(new ArrayList<>());

    private void loadImages(final List<cgImage> images, final int progressMessage, final boolean offline) {
        count = images.size();
        progressBar.setMax(count);
        progressBar.setProgress(0);
        progressBar.setVisibility(View.VISIBLE);

        for (final cgImage img : images) {
            LinearLayout rowView = (LinearLayout) inflater.inflate(R.layout.cache_image_item, null);

            TextView titleView = rowView.findViewById(R.id.title);
            titleView.setText(Html.fromHtml(img.getTitle(), Html.FROM_HTML_MODE_LEGACY));

            if (StringUtils.isNotBlank(img.getDescription())) {
                TextView descView = rowView.findViewById(R.id.description);
                descView.setText(
                    Html.fromHtml(img.getDescription(), Html.FROM_HTML_MODE_LEGACY),
                    TextView.BufferType.SPANNABLE);
                descView.setVisibility(View.VISIBLE);
            }

            new AsyncImgLoader(rowView, img, offline).execute();
            imagesView.addView(rowView);
        }
    }

    private class AsyncImgLoader extends AsyncTask<Void, Void, BitmapDrawable> {

        private final LinearLayout view;
        private final cgImage img;
        private final boolean offline;

        AsyncImgLoader(LinearLayout view, cgImage img, boolean offline) {
            this.view = view;
            this.img = img;
            this.offline = offline;
        }

        @Override
        protected BitmapDrawable doInBackground(Void... params) {
            HtmlImage imgGetter = new HtmlImage(cgeoimages.this, geocode, true, offline ? 1 : 0, false);
            return imgGetter.getDrawable(img.getUrl());
        }

        @Override
        protected void onPostExecute(BitmapDrawable image) {
            if (image != null) {
                bitmaps.add(image.getBitmap());
                ImageView imageView = (ImageView) inflater.inflate(R.layout.image_item, null);
                Rect bounds = image.getBounds();

                imageView.setImageResource(R.drawable.image_not_loaded);
                imageView.setClickable(true);
                imageView.setOnClickListener(v -> viewImageInStandardApp(image));
                registerForContextMenu(imageView);
                imageView.setImageDrawable(image);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(new LayoutParams(bounds.width(), bounds.height()));

                view.addView(imageView);
                images.put(imageView.getId(), img);
            }

            synchronized (cgeoimages.this) {
                countDone++;
                progressBar.setProgress(countDone);
                if (countDone >= count) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        int imgType = UNKNOWN_TYPE;
        if (extras != null) {
            geocode = extras.getString("geocode");
            imgType = extras.getInt("type", 0);
        }

        if (extras == null || geocode == null) {
            showToast("Sorry, c:geo forgot for what cache you want to load spoiler images.");
            finish();
            return;
        }

        if (imgType != SPOILER_IMAGES && imgType != LOG_IMAGES) {
            showToast("Sorry, can't load unknown image type.");
            finish();
            return;
        }

        setTheme();
        setContentView(R.layout.spoilers);
        setTitle(getString(imgType == SPOILER_IMAGES
            ? R.string.cache_spoiler_images_title
            : R.string.cache_log_images_title));

        inflater = getLayoutInflater();
        imagesView = findViewById(R.id.spoiler_list);

        ArrayList<cgImage> imageList = extras.getParcelableArrayList("images");
        if (CollectionUtils.isEmpty(imageList)) {
            showToast(getString(R.string.warn_load_images));
            finish();
            return;
        }

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setMax(imageList.size());
        progressBar.setVisibility(View.VISIBLE);

        boolean offline = app.isOffline(geocode, null)
            && (imgType == SPOILER_IMAGES || Settings.isStoreLogImages());

        loadImages(imageList,
            imgType == SPOILER_IMAGES
                ? R.string.cache_spoiler_images_loading
                : R.string.cache_log_images_loading,
            offline);
    }

    @Override
    protected void onDestroy() {
        for (Bitmap b : bitmaps) {
            b.recycle();
        }
        bitmaps.clear();
        super.onDestroy();
    }

    private void viewImageInStandardApp(BitmapDrawable image) {
        File file = LocalStorage.getStorageFile(null, "temp.jpg", false, true);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            image.getBitmap().compress(CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            Log.e(Settings.tag, "cgeoimages.handleMessage.onClick: " + e);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = FileProvider.getUriForFile(
            this,
            getApplicationContext().getPackageName() + ".provider",
            file);
        intent.setDataAndType(uri, "image/jpeg");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);

        if (file.exists()) {
            file.deleteOnExit();
        }
    }

    public static void startActivityLogImages(Context fromActivity, String geocode, ArrayList<cgImage> logImages) {
        startActivity(fromActivity, geocode, logImages, LOG_IMAGES);
    }

    public static void startActivitySpoilerImages(Context fromActivity, String geocode, ArrayList<cgImage> spoilers) {
        startActivity(fromActivity, geocode, spoilers, SPOILER_IMAGES);
    }

    private static void startActivity(Context fromActivity, String geocode, ArrayList<cgImage> images, int imageType) {
        Intent intent = new Intent(fromActivity, cgeoimages.class);
        intent.putExtra("geocode", geocode.toUpperCase());
        intent.putExtra("type", imageType);
        intent.putParcelableArrayListExtra("images", images);
        fromActivity.startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(getString(R.string.cache_image));
        menu.add(0, MENU_FILE, 0, getString(R.string.cache_image_open_file));
        menu.add(0, MENU_BROWSER, 0, getString(R.string.cache_image_open_browser));
        ImageView view = (ImageView) v;
        currentDrawable = (BitmapDrawable) view.getDrawable();
        currentImage = images.get(view.getId());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_FILE:
                viewImageInStandardApp(currentDrawable);
                return true;
            case MENU_BROWSER:
                if (currentImage != null) {
                    currentImage.openInBrowser(this);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
