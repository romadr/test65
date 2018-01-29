package ru.test65.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;
import ru.test65.App;
import ru.test65.data.DataManager;
import ru.test65.di.ApplicationContext;
import ru.test65.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    Cicerone<Router> cicerone();

    DataManager getDataManager();

}