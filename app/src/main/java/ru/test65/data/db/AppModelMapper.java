package ru.test65.data.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.test65.data.bo.Workman;
import ru.test65.data.db.model.WorkmanModel;
import ru.test65.di.ApplicationContext;

public class AppModelMapper implements ModelMapper {

    private Context context;

    @Inject
    public AppModelMapper(@ApplicationContext Context context) {
        this.context = context;
    }


    @Override
    public List<WorkmanModel> toCategoriesModelList(List<Workman> categories) {
        if (categories == null) return null;

        final List<WorkmanModel> res = new ArrayList<>();
        for (Workman workman : categories) {
            final WorkmanModel categoryModel = new WorkmanModel();

            categoryModel.setId(workman.id);


            res.add(categoryModel);
        }

        return res;
    }

    @Override
    public List<Workman> toCategoriesBO(List<WorkmanModel> workmanModelList) {
        if (workmanModelList == null) return null;

        List<Workman> res = new ArrayList<>();

        for (WorkmanModel workmanModel : workmanModelList) {
            Workman workman = new Workman();
            workman.id = workmanModel.getId();

            res.add(workman);
        }

        return res;
    }

}
