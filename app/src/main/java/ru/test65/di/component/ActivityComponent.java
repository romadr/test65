package ru.test65.di.component;


import dagger.Component;
import ru.test65.di.PerActivity;
import ru.test65.di.module.ActivityModule;
import ru.test65.ui.main.MainActivity;
import ru.test65.ui.specialty.SpecialtyListFragment;
import ru.test65.ui.workman.card.WorkmanCardFragment;
import ru.test65.ui.workman.list.WorkmanListFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(SpecialtyListFragment specialtyListFragment);

    void inject(WorkmanListFragment workmanListFragment);

    void inject(WorkmanCardFragment workmanCardFragment);

}
