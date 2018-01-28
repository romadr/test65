package ru.test65.data.db;


import java.util.List;

import io.reactivex.Observable;
import ru.test65.data.bo.Specialty;
import ru.test65.data.bo.Workman;


public interface DbHelper {

    Observable<List<Workman>> getWorkmansBySpecialty(Long specialtyId);

    Observable<Workman> getWorkmanById(Long workmanId);

    Observable<List<Specialty>> getAllSpecialty();

    Observable<Boolean> saveWorkmans(List<Workman> workmans);

}
