package ru.test65.data.db;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ru.test65.data.bo.Workman;
import ru.test65.data.db.model.DaoMaster;
import ru.test65.data.db.model.DaoSession;


@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;
    private final AppModelMapper appModelMapper;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper, AppModelMapper appModelMapper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
        this.appModelMapper = appModelMapper;
    }


    @Override
    public Observable<List<Workman>> getAllCategories() {
        return Observable.fromCallable(() -> mDaoSession.getWorkmanModelDao()
                .loadAll())
                .map(appModelMapper::toCategoriesBO);
    }

    @Override
    public Observable<Boolean> insertCategories(List<Workman> categories) {
        return Observable.just(categories)
                .map(appModelMapper::toCategoriesModelList)
                .map(categoryModels -> {
                    mDaoSession.getWorkmanModelDao().deleteAll();
                    mDaoSession.getWorkmanModelDao().insertInTx(categoryModels);
                    return true;
                });
    }


}
