package ru.test65.data.db;

import java.util.List;

import ru.test65.data.bo.Workman;
import ru.test65.data.db.model.WorkmanModel;

public interface ModelMapper {

    List<WorkmanModel> toCategoriesModelList(List<Workman> categories);

    List<Workman> toCategoriesBO(List<WorkmanModel> products);

}
