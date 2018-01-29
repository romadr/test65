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

package ru.test65.ui.workman.list;

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
import ru.test65.data.bo.Workman;
import ru.test65.ui.Screens;
import ru.test65.ui.base.BasePresenter;
import ru.test65.ui.workman.card.WorkmanCardFragment;
import timber.log.Timber;


public class WorkmanListPresenter<V extends WorkmanListMvpView> extends BasePresenter<V>
        implements WorkmanListMvpPresenter<V> {

    @Inject
    Cicerone<Router> cicerone;

    @Inject
    public WorkmanListPresenter(DataManager dataManager,
                                CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void initWithSpecialty(Specialty specialty) {

        getDataManager().getWorkmansBySpecialty(specialty.getSpecialtyId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(it -> getMvpView().showLoading())
                .doOnTerminate(() -> getMvpView().hideLoading())
                .subscribe(data -> getMvpView().showWorkmansList(data),
                        ex -> {
                            getMvpView().showMessage(R.string.data_load_error_message);
                            Timber.e(ex);
                        });
    }

    @Override
    public void onWorkmanClick(Workman workman) {
        final Bundle data = new Bundle();
        data.putSerializable(WorkmanCardFragment.WORKMAN_EXTRA, workman);
        cicerone.getRouter().navigateTo(Screens.WORKMAN_CARD, data);

    }
}
