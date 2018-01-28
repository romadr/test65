package ru.test65;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.NoSubscriberEvent;
import org.greenrobot.eventbus.Subscribe;

import ru.test65.di.component.ApplicationComponent;
import ru.test65.di.component.DaggerApplicationComponent;
import ru.test65.di.module.ApplicationModule;
import timber.log.Timber;

public class App extends Application {

    private static ApplicationComponent mApplicationComponent;

    public static ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);

        //init DI
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        EventBus.getDefault().register(new DeadLogger());

    }

    private static class DeadLogger {
        @Subscribe
        @SuppressWarnings("unused")
        public void onEvent(NoSubscriberEvent deadEvent) {
            Timber.e("deadEvent: %s", deadEvent.originalEvent.getClass().getCanonicalName());
        }
    }

}
