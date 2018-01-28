package ru.test65.ui.base;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ru.test65.data.DataManager;


public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private final DataManager mDataManager;
    private final CompositeDisposable mCompositeDisposable;
    private V mMvpView;

    @Inject
    public BasePresenter(DataManager dataManager, CompositeDisposable mCompositeDisposable) {
        this.mDataManager = dataManager;
        this.mCompositeDisposable = mCompositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
//        EventBus.getDefault().unregister(this);
    }


    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
