/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package ru.test65.ui.specialty;

import android.os.Bundle;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;
import ru.test65.R;
import ru.test65.data.DataManager;
import ru.test65.data.bo.Specialty;
import ru.test65.ui.Screens;
import ru.test65.ui.base.BasePresenter;
import ru.test65.ui.workman.list.WorkmanListFragment;
import timber.log.Timber;


public class SpecialtyListPresenter<V extends SpecialtyListMvpView> extends BasePresenter<V>
        implements SpecialtyListMvpPresenter<V> {

    @Inject
    Cicerone<Router> cicerone;

    @Inject
    public SpecialtyListPresenter(DataManager dataManager,
                                  CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        loadAllSpecialty();
    }

    @Override
    public void onSpecialtyClick(Specialty specialty) {
        final Bundle data = new Bundle();
        data.putSerializable(WorkmanListFragment.SPECIALTY_EXTRA, specialty);
        cicerone.getRouter().navigateTo(Screens.WORKMAN_LIST, data);
    }

    private void loadAllSpecialty() {
        getCompositeDisposable().add(getDataManager().getAllSpecialty()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(it -> getMvpView().showLoading())
                .doOnTerminate(() -> getMvpView().hideLoading())
                .subscribe(data -> getMvpView().showSpecialtyList(data),
                        ex -> {
                            getMvpView().showMessage(R.string.data_load_error_message);
                            Timber.e(ex);
                        }));
    }
}

