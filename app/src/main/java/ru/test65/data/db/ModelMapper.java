package ru.test65.data.db;

import ru.test65.data.bo.Specialty;
import ru.test65.data.bo.Workman;
import ru.test65.data.db.model.SpecialtyModel;
import ru.test65.data.db.model.WorkmanModel;

public interface ModelMapper {

    WorkmanModel toWorkmanModel(Workman workman);

    Workman toWorkmanBO(WorkmanModel workmanModel);

    SpecialtyModel toSpecialtyModel(Specialty specialty);

    Specialty toSpecialtyBO(SpecialtyModel specialtyModel);

}
