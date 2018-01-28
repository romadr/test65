package ru.test65.data.remote;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ru.test65.data.remote.model.LoadDataRes;
import ru.test65.di.ApplicationContext;

@Singleton
public class AppApiHelper implements ApiHelper {

    private final Context context;
    private RemoteApi remoteApi;

    @Inject
    public AppApiHelper(@ApplicationContext Context context, RemoteApi remoteApi) {
        this.context = context;
        this.remoteApi = remoteApi;
    }

    @Override
    public Observable<LoadDataRes> loadData() {
        return remoteApi.loadData();
    }

}
