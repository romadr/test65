package ru.test65.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ru.test65.di.ActivityContext;
import ru.test65.di.PerActivity;
import ru.test65.ui.main.MainActivityMvpPresenter;
import ru.test65.ui.main.MainActivityMvpView;
import ru.test65.ui.main.MainActivityPresenter;
import ru.test65.ui.specialty.SpecialtyListMvpPresenter;
import ru.test65.ui.specialty.SpecialtyListMvpView;
import ru.test65.ui.specialty.SpecialtyListPresenter;
import ru.test65.ui.workman.card.WorkmanCardMvpPresenter;
import ru.test65.ui.workman.card.WorkmanCardMvpView;
import ru.test65.ui.workman.card.WorkmanCardPresenter;
import ru.test65.ui.workman.list.WorkmanListMvpPresenter;
import ru.test65.ui.workman.list.WorkmanListMvpView;
import ru.test65.ui.workman.list.WorkmanListPresenter;


@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    MainActivityMvpPresenter<MainActivityMvpView> provideMainActivityMvpPresenter(MainActivityPresenter<MainActivityMvpView> presenter) {
        return presenter;
    }

    @Provides
    SpecialtyListMvpPresenter<SpecialtyListMvpView> provideSpecialtyListPresenter(
            SpecialtyListPresenter<SpecialtyListMvpView> presenter) {
        return presenter;
    }

    @Provides
    WorkmanListMvpPresenter<WorkmanListMvpView> provideWorkmanListPresenter(
            WorkmanListPresenter<WorkmanListMvpView> presenter) {
        return presenter;
    }

    @Provides
    WorkmanCardMvpPresenter<WorkmanCardMvpView> provideWorkmanCardPresenter(
            WorkmanCardPresenter<WorkmanCardMvpView> presenter) {
        return presenter;
    }


}
