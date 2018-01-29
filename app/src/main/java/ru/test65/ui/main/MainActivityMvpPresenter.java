package ru.test65.ui.main;


import ru.test65.ui.base.MvpPresenter;

public interface MainActivityMvpPresenter<V extends MainActivityMvpView> extends MvpPresenter<V> {

    void onBackClick();
}
