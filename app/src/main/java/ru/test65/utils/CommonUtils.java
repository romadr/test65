package ru.test65.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.io.File;
import java.text.ChoiceFormat;
import java.util.Date;

import ru.test65.R;

public class CommonUtils {


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void createFolderIfNotExists(String path) {
        final File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = ProgressDialog.show(context, "",
                context.getString(R.string.loading_progress_text), true);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;

    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static String getAgeSuffix(int age) {
        double[] limits = {0, 1, 2, 5};
        String[] strings = {"лет", "год", "года", "лет"};
        ChoiceFormat format = new ChoiceFormat(limits, strings);
        int rule = 11 <= (age % 100) && (age % 100) <= 14 ? age : age % 10;
        return format.format(rule);
    }

    public static int calculateAge(Date date) {
        final LocalDate birthdate = new LocalDate(date);
        final LocalDate now = new LocalDate();
        return Years.yearsBetween(birthdate, now).getYears();
    }

    public static String normalizeName(String raw) {
        if (raw == null || raw.isEmpty()) return raw;
        return raw.substring(0, 1).toUpperCase() + raw.substring(1).toLowerCase();
    }


}
