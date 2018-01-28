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

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.test65.R;
import ru.test65.data.DataManager;
import ru.test65.ui.base.BasePresenter;
import timber.log.Timber;


public class SpecialtyListPresenter<V extends SpecialtyListMvpView> extends BasePresenter<V>
        implements SpecialtyListMvpPresenter<V> {

    @Inject
    public SpecialtyListPresenter(DataManager dataManager,
                                  CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        getDataManager().loadData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((data) -> Timber.d(data.toString()),
                        (exception) -> getMvpView().onError(R.string.on_data_loading_error));
    }
}
