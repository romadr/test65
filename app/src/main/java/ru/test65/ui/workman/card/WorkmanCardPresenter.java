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

package ru.test65.ui.workman.card;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ru.test65.data.DataManager;
import ru.test65.ui.base.BasePresenter;


public class WorkmanCardPresenter<V extends WorkmanCardMvpView> extends BasePresenter<V>
        implements WorkmanCardMvpPresenter<V> {

    @Inject
    public WorkmanCardPresenter(DataManager dataManager,
                                CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }
}
