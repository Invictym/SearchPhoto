package com.kalatsky.searchphotos.api;

import com.kalatsky.searchphotos.models.ApiReq;
import com.kalatsky.searchphotos.models.PhotoLocation;

import dagger.Module;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


@Module
public class RetrofitBilder {

  private static final String API_KEY = "2a0f092f7d1c424563334020b6cfbc0a";
  private static final String FORMAT = "json";
  private static final String URL = "https://api.flickr.com/services/";
  private static final int GEO_LOCATION = 1;
  private static final int CALL_BACK = 1;

  public static Observable<ApiReq> searchPhotosRX(String text) {
    return new Retrofit.Builder()
            .baseUrl(URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlickrAPI.class).searchPhotosRX("flickr.photos.search", API_KEY, text, GEO_LOCATION, FORMAT, CALL_BACK)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

  }

  public static Observable<PhotoLocation> getPhotoLocationRX(long id) {
    return new Retrofit.Builder()
            .baseUrl(URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlickrAPI.class).getPhotoLocationRX("flickr.photos.geo.getLocation", API_KEY, id, FORMAT, CALL_BACK)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }
}
