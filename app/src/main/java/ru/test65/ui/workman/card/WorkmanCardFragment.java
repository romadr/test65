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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.test65.R;
import ru.test65.data.bo.Specialty;
import ru.test65.data.bo.Workman;
import ru.test65.di.component.ActivityComponent;
import ru.test65.ui.base.BaseFragment;
import ru.test65.utils.AppConstants;
import ru.test65.utils.CommonUtils;


public class WorkmanCardFragment extends BaseFragment implements WorkmanCardMvpView {

    public static final String TAG = "WorkmanListFragment";
    public static final String WORKMAN_EXTRA = "WORKMAN_EXTRA";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.specialtyText)
    TextView specialtyText;

    @BindView(R.id.birthdayText)
    TextView birthdayText;

    @BindView(R.id.ageText)
    TextView ageText;

    @BindView(R.id.icon)
    ImageView icon;

    @Inject
    WorkmanCardMvpPresenter<WorkmanCardMvpView> mPresenter;

    public static WorkmanCardFragment newInstance(Object data) {
        Bundle args = new Bundle();
        if (data instanceof Bundle) {
            args.putAll((Bundle) data);
        }
        WorkmanCardFragment fragment = new WorkmanCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workman_card, container, false);

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
        final Workman workman = (Workman) getArguments().getSerializable(WORKMAN_EXTRA);
        assert workman != null;

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(workman.getLName() + " " + workman.getFName());
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }

        //TODO Тут я уже добил на скорую руку. Устал =)
        List<Specialty> specialtyList = workman.getSpecialty();
        if (specialtyList != null) {
            final StringBuilder specialtyBuilder = new StringBuilder();
            for (int i = 0; i < specialtyList.size(); i++) {
                specialtyBuilder.append(specialtyList.get(i).getName());
                if (i + 1 < specialtyList.size()) {
                    specialtyBuilder.append(", ");
                }
            }
            specialtyText.setText(specialtyBuilder.toString());
        }

        if (workman.getBirthday() != null) {
            int age = CommonUtils.calculateAge(workman.getBirthday());
            ageText.setText(age + " " + CommonUtils.getAgeSuffix(age));
        } else {
            ageText.setText("-");
        }

        if (workman.getBirthday() != null) {
            final DateFormat df2 = new SimpleDateFormat(AppConstants.GSON_API_DATE_FORMAT2);
            birthdayText.setText(df2.format(workman.getBirthday()) + " г.");
        }

        if (workman.getAvatrUrl() != null && !workman.getAvatrUrl().isEmpty()) {
            Picasso.with(getActivity()).load(workman.getAvatrUrl()).placeholder(R.drawable.ic_person_black_48dp).into(icon);
        } else {
            Picasso.with(getActivity()).load(R.drawable.ic_person_black_48dp).into(icon);
        }


    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
