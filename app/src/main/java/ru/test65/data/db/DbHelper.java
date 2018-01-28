package ru.test65.data.db;


import java.util.List;

import io.reactivex.Observable;
import ru.test65.data.bo.Workman;


public interface DbHelper {

    Observable<List<Workman>> getAllCategories();

    Observable<Boolean> insertCategories(List<Workman> categories);

}
