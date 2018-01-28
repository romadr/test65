package ru.test65.data.db;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ru.test65.data.bo.Specialty;
import ru.test65.data.bo.Workman;
import ru.test65.data.db.model.DaoMaster;
import ru.test65.data.db.model.DaoSession;
import ru.test65.data.db.model.WorkmanModelDao;


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
    public Observable<List<Workman>> getWorkmansBySpecialty(Long specialtyId) {
        return Observable.fromCallable(() -> mDaoSession.getWorkmanModelDao().queryBuilder().where(WorkmanModelDao.Properties.SpecialtyId.eq(specialtyId)).list())
                .flatMap(Observable::fromIterable)
                .map(appModelMapper::toWorkmanBO)
                .toList().toObservable();
    }

    @Override
    public Observable<Workman> getWorkmanById(Long workmanId) {
        return Observable.fromCallable(() -> mDaoSession.getWorkmanModelDao().load(workmanId))
                .map(appModelMapper::toWorkmanBO);
    }

    @Override
    public Observable<List<Specialty>> getAllSpecialty() {
        return Observable.fromCallable(() -> mDaoSession.getSpecialtyModelDao().loadAll())
                .flatMap(Observable::fromIterable)
                .map(appModelMapper::toSpecialtyBO)
                .toList().toObservable();
    }


    @Override
    public Observable<Boolean> saveWorkmans(List<Workman> workmans) {
        return Observable.just(
                mDaoSession.callInTxNoException(() -> {
                    for (Workman workmen : workmans) {
                        if (workmen.getSpecialty() != null) {
                            mDaoSession.getSpecialtyModelDao().save(appModelMapper.toSpecialtyModel(workmen.getSpecialty()));
                        }
                        mDaoSession.getWorkmanModelDao().save(appModelMapper.toWorkmanModel(workmen));
                    }
                    return true;
                })
        );
    }


}
