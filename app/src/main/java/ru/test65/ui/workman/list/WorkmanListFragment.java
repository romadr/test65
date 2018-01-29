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
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;
import ru.test65.R;
import ru.test65.data.bo.Specialty;
import ru.test65.data.bo.Workman;
import ru.test65.di.component.ActivityComponent;
import ru.test65.ui.base.BaseFragment;


public class WorkmanListFragment extends BaseFragment implements WorkmanListMvpView {

    public static final String SPECIALTY_EXTRA = "SPECIALTY_EXTRA";
    public static final String TAG = "WorkmanListFragment";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.workmanList)
    RecyclerView workmanListRecyclerView;

    @Inject
    Cicerone<Router> cicerone;

    @Inject
    WorkmanListMvpPresenter<WorkmanListMvpView> mPresenter;

    private WorkmanListAdapter adapter;

    public static WorkmanListFragment newInstance(Object data) {
        Bundle args = new Bundle();
        if (data instanceof Bundle) {
            args.putAll((Bundle) data);
        }
        WorkmanListFragment fragment = new WorkmanListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workman_list, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        return view;
    }

    @Override
    protected void setUp(View view) {
        final Specialty specialty = (Specialty) getArguments().getSerializable(SPECIALTY_EXTRA);
        assert specialty != null;

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(specialty.getName());
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        workmanListRecyclerView.setLayoutManager(layoutManager);
        adapter = new WorkmanListAdapter(getActivity(), workman -> {
            mPresenter.onWorkmanClick(workman);
        });

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        workmanListRecyclerView.addItemDecoration(mDividerItemDecoration);

        workmanListRecyclerView.setAdapter(adapter);

        mPresenter.initWithSpecialty(specialty);
    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void showWorkmansList(List<Workman> data) {
        if (adapter == null) return;
        adapter.setData(data);
        adapter.notifyDataSetChanged();

    }
}
