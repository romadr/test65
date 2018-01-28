package ru.test65.data.remote;


import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.test65.data.remote.model.LoadDataRes;

public interface ApiHelper {

    Observable<LoadDataRes> loadData();

}
