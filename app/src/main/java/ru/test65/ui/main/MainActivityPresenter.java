package ru.test65.ui.main;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;
import ru.test65.R;
import ru.test65.data.DataManager;
import ru.test65.ui.Screens;
import ru.test65.ui.base.BasePresenter;
import timber.log.Timber;

public class MainActivityPresenter<V extends MainActivityMvpView> extends BasePresenter<V>
        implements MainActivityMvpPresenter<V> {

    @Inject
    Cicerone<Router> cicerone;

    @Inject
    public MainActivityPresenter(DataManager dataManager, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mCompositeDisposable);
    }

    @Override
    public void onBackClick() {
        cicerone.getRouter().exit();
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        loadData();
    }

    private void loadData() {
        getCompositeDisposable().add(getDataManager().loadData()
                .map(loadDataRes -> getDataManager().saveWorkmans(loadDataRes.response))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(it -> getMvpView().showLoading())
                .doOnTerminate(() -> {
                    getMvpView().hideLoading();
                    cicerone.getRouter().newRootScreen(Screens.SPECIALTY_LIST);
                })
                .subscribe(data -> getMvpView().showMessage(R.string.data_load_success_message),
                        ex -> {
                            getMvpView().showMessage(R.string.data_load_error_message);
                            Timber.e(ex);
                        }));
    }
}
