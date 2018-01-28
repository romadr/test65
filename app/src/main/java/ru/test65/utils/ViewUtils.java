package ru.test65.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;
import android.widget.Toast;


public final class ViewUtils {

    private ViewUtils() {
        // This utility class is not publicly instantiable
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static Toast createToast(Activity context, String message) {
        return Toast.makeText(context, message, Toast.LENGTH_LONG);
    }

    public static void setDrawableBackgroundColor(View view, int colorId) {
        if (view == null) return;

        final Drawable background = view.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(colorId);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(colorId);
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable) background).setColor(colorId);
        }
    }


}
