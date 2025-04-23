import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import java.util.Objects;

public final class CalligraphyUtils {
    private static final int[] FONT_PATH_ATTR = new int[1];

    public static String pullFontPathFromStyle(Context context, AttributeSet attrs, int attributeId) {
        Objects.requireNonNull(context, "Context must not be null");
        FONT_PATH_ATTR[0] = attributeId;
        TypedArray ta = null;
        try {
            ta = context.obtainStyledAttributes(attrs, FONT_PATH_ATTR);
            return ta.getString(0);
        } catch (Resources.NotFoundException e) {
            return null;
        } finally {
            if (ta != null) {
                ta.recycle();
            }
        }
    }
}
