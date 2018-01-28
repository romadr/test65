package ru.test65.data;


import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ru.test65.data.bo.Workman;
import ru.test65.data.db.DbHelper;
import ru.test65.data.prefs.PreferencesHelper;
import ru.test65.data.remote.ApiHelper;
import ru.test65.di.ApplicationContext;


@Singleton
public class AppDataManager implements DataManager {

    private final Context context;
    private PreferencesHelper preferencesHelper;
    private ApiHelper apiHelper;
    private DbHelper dbHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper,
                          DbHelper dbHelper) {
        this.context = context;
        this.preferencesHelper = preferencesHelper;
        this.apiHelper = apiHelper;
        this.dbHelper = dbHelper;
    }


    @Override
    public String getApiServer() {
        return preferencesHelper.getApiServer();
    }

    @Override
    public void setApiServer(String url) {
        preferencesHelper.setApiServer(url);
    }

    @Override
    public Observable<List<Workman>> getAllCategories() {
        return null;
    }

    @Override
    public Observable<Boolean> insertCategories(List<Workman> categories) {
        return null;
    }
}
