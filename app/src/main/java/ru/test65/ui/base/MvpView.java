package ru.test65.ui.base;

import android.support.annotation.StringRes;


public interface MvpView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void hideKeyboard();

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);


}
