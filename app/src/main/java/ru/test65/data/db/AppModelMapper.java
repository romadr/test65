package ru.test65.data.db;

import android.content.Context;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.test65.data.bo.Specialty;
import ru.test65.data.bo.Workman;
import ru.test65.data.db.model.SpecialtyModel;
import ru.test65.data.db.model.WorkmanModel;
import ru.test65.di.ApplicationContext;

public class AppModelMapper implements ModelMapper {

    private Context context;

    @Inject
    public AppModelMapper(@ApplicationContext Context context) {
        this.context = context;
    }

    @Override
    public WorkmanModel toWorkmanModel(Workman workman) {
        if (workman == null) return null;
        WorkmanModel workmanModel = new WorkmanModel();
        workmanModel.setId(workman.getId());
        workmanModel.setFName(workman.getFName());
        workmanModel.setLName(workman.getLName());
        workmanModel.setBirthday(workman.getBirthday());
        workmanModel.setAvatrUrl(workman.getAvatrUrl());
        if (workman.getSpecialty() != null) {
            workmanModel.resetSpecialtyList();
            for (Specialty specialty : workman.getSpecialty()) {
                workmanModel.getSpecialtyList().add(toSpecialtyModel(specialty));
            }
        }
        return workmanModel;
    }

    @Override
    public Workman toWorkmanBO(WorkmanModel workmanModel) {
        if (workmanModel == null) return null;
        Workman workman = new Workman();
        workman.setId(workmanModel.getId());
        workman.setFName(workmanModel.getFName());
        workman.setLName(workmanModel.getLName());
        workman.setBirthday(workmanModel.getBirthday());
        workman.setAvatrUrl(workmanModel.getAvatrUrl());
        if (workmanModel.getSpecialtyList() != null) {
            workman.setSpecialty(new ArrayList<>());
            for (SpecialtyModel specialtyModel : workmanModel.getSpecialtyList()) {
                workman.getSpecialty().add(toSpecialtyBO(specialtyModel));
            }
        }
        return workman;
    }

    @Override
    public SpecialtyModel toSpecialtyModel(Specialty specialty) {
        if (specialty == null) return null;
        SpecialtyModel specialtyModel = new SpecialtyModel();
        specialtyModel.setSpecialtyId(specialty.getSpecialtyId());
        specialtyModel.setName(specialty.getName());
        return specialtyModel;
    }

    @Override
    public Specialty toSpecialtyBO(SpecialtyModel specialtyModel) {
        if (specialtyModel == null) return null;
        Specialty specialty = new Specialty();
        specialty.setSpecialtyId(specialtyModel.getSpecialtyId());
        specialty.setName(specialtyModel.getName());
        return specialty;
    }
}
