package ru.test65.ui.main;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ru.test65.data.DataManager;
import ru.test65.ui.base.BasePresenter;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;

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
}
