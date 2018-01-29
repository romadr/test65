package ru.test65.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.test65.di.ApplicationContext;
import ru.test65.di.PreferenceInfo;
import ru.test65.utils.AppConstants;


@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String API_SERVER = "API_SERVER";

    private final SharedPreferences mPrefs;
    private final Gson gson;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName, Gson gson) {
        this.gson = gson;
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getApiServer() {
        return mPrefs.getString(API_SERVER, AppConstants.DEF_API_SERVER);
    }

    @Override
    public void setApiServer(String url) {
        mPrefs.edit().putString(API_SERVER, url).apply();
    }

}
