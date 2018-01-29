package ru.test65.data;


import ru.test65.data.db.DbHelper;
import ru.test65.data.prefs.PreferencesHelper;
import ru.test65.data.remote.ApiHelper;

public interface DataManager extends ApiHelper, PreferencesHelper, DbHelper {

}
