package ru.test65.di.module;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;
import ru.test65.BuildConfig;
import ru.test65.data.AppDataManager;
import ru.test65.data.DataManager;
import ru.test65.data.db.AppDbHelper;
import ru.test65.data.db.AppModelMapper;
import ru.test65.data.db.DbHelper;
import ru.test65.data.db.ModelMapper;
import ru.test65.data.prefs.AppPreferencesHelper;
import ru.test65.data.prefs.PreferencesHelper;
import ru.test65.data.remote.ApiHelper;
import ru.test65.data.remote.AppApiHelper;
import ru.test65.data.remote.RemoteApi;
import ru.test65.di.ApplicationContext;
import ru.test65.di.DatabaseInfo;
import ru.test65.di.PreferenceInfo;
import ru.test65.utils.AppConstants;
import timber.log.Timber;


@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Cicerone<Router> provideCicerone() {
        return Cicerone.create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_PATH;
    }


    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }


    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }


    @Provides
    @Singleton
    @SuppressLint("SimpleDateFormat")
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            final DateFormat df1 = new SimpleDateFormat(AppConstants.GSON_API_DATE_FORMAT);
            final DateFormat df2 = new SimpleDateFormat(AppConstants.GSON_API_DATE_FORMAT2);

            @Override
            public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                    throws JsonParseException {

                //O_o
                try {
                    final String asString = json.getAsString();
                    if (asString == null || asString.isEmpty()) return null;

                    int yearPartLength = asString.substring(0, asString.indexOf("-")).length();
                    if (yearPartLength < 4) {
                        return df2.parse(asString);
                    } else {
                        return df1.parse(asString);
                    }
                } catch (Exception ex) {
                    Timber.e(ex);
                    return null;
                }

            }
        });
        gsonBuilder.enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat(AppConstants.GSON_API_DATE_FORMAT);
        //.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC, Modifier.PRIVATE);

        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    RemoteApi provideRemoteApi(Retrofit retrofit) {
        return retrofit.create(RemoteApi.class);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ModelMapper provideModelMapper(AppModelMapper appModelMapper) {
        return appModelMapper;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(AppConstants.DEFAULT_API_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(AppConstants.DEFAULT_API_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(AppConstants.DEFAULT_API_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient, PreferencesHelper preferencesHelper) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(preferencesHelper.getApiServer())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
