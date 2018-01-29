package ru.test65.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.test65.R;
import ru.test65.ui.Screens;
import ru.test65.ui.base.BaseActivity;
import ru.test65.ui.specialty.SpecialtyListFragment;
import ru.test65.ui.workman.card.WorkmanCardFragment;
import ru.test65.ui.workman.list.WorkmanListFragment;
import ru.test65.utils.AppConstants;

public class MainActivity extends BaseActivity implements MainActivityMvpView {

    @Inject
    Cicerone<Router> cicerone;

    @Inject
    MainActivityMvpPresenter<MainActivityMvpView> mPresenter;
    boolean doubleBackToExitPressedOnce = false;
    private Navigator navigator = new SupportAppNavigator(this, R.id.container) {
        @Override
        protected Intent createActivityIntent(Context context, String screenKey, Object data) {
            return null;
        }

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Screens.SPECIALTY_LIST:
                    return SpecialtyListFragment.newInstance();
                case Screens.WORKMAN_CARD:
                    return WorkmanCardFragment.newInstance(data);
                case Screens.WORKMAN_LIST:
                    return WorkmanListFragment.newInstance(data);
            }
            return null;
        }

        @Override
        protected void setupFragmentTransactionAnimation(
                Command command,
                Fragment currentFragment,
                Fragment nextFragment,
                FragmentTransaction fragmentTransaction) {
            fragmentTransaction.setCustomAnimations(
                    R.anim.slide_in_right, R.anim.slide_out_left,
                    R.anim.slide_in_left, R.anim.slide_out_right);
        }

        @Override
        protected void exit() {
            if (doubleBackToExitPressedOnce) {
                super.exit();
                return;
            }

            doubleBackToExitPressedOnce = true;
            Toast.makeText(MainActivity.this, R.string.double_click_to_exit_message, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, AppConstants.DOUBLE_CLICK_TO_EXIT_TIMEOUT);
        }
    };

    @Override
    public void onBackPressed() {
        mPresenter.onBackClick();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mPresenter.onBackClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));

        setUp();

        mPresenter.onAttach(this);
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cicerone.getNavigatorHolder().setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        cicerone.getNavigatorHolder().removeNavigator();
    }


}
