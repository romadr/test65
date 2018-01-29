package ru.test65.data.db;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ru.test65.data.bo.Specialty;
import ru.test65.data.bo.Workman;
import ru.test65.data.db.model.DaoMaster;
import ru.test65.data.db.model.DaoSession;
import ru.test65.data.db.model.JoinWorkmansWithSpecialtyModel;
import ru.test65.data.db.model.JoinWorkmansWithSpecialtyModelDao;
import ru.test65.data.db.model.SpecialtyModel;
import ru.test65.data.db.model.WorkmanModel;


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
        return Observable.fromCallable(() -> {
            final QueryBuilder<WorkmanModel> queryBuilder = mDaoSession.getWorkmanModelDao().queryBuilder();
            queryBuilder.join(JoinWorkmansWithSpecialtyModel.class, JoinWorkmansWithSpecialtyModelDao.Properties.WorkmanId)
                    .where(JoinWorkmansWithSpecialtyModelDao.Properties.SpecialtyId.eq(specialtyId));
            return queryBuilder.list();
        })
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

                    mDaoSession.getSpecialtyModelDao().deleteAll();
                    mDaoSession.getWorkmanModelDao().deleteAll();

                    for (Workman workmen : workmans) {
                        WorkmanModel entity = appModelMapper.toWorkmanModel(workmen);
                        mDaoSession.insertOrReplace(entity);
                        if (workmen.getSpecialty() != null) {
                            entity.resetSpecialtyList();
                            for (Specialty specialty : workmen.getSpecialty()) {
                                final SpecialtyModel specialtyModel = appModelMapper.toSpecialtyModel(specialty);
                                mDaoSession.insertOrReplace(specialtyModel);

                                JoinWorkmansWithSpecialtyModel joinWorkmansWithSpecialtyModel = new JoinWorkmansWithSpecialtyModel();
                                joinWorkmansWithSpecialtyModel.setSpecialtyId(specialtyModel.getSpecialtyId());
                                joinWorkmansWithSpecialtyModel.setWorkmanId(entity.getId());
                                mDaoSession.insertOrReplace(joinWorkmansWithSpecialtyModel);

                                entity.getSpecialtyList().add(specialtyModel);
                            }
                        }
                    }

                    return true;
                })
        );
    }


}
