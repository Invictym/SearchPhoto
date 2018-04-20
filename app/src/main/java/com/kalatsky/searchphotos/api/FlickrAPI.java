package com.kalatsky.searchphotos.api;

import com.kalatsky.searchphotos.models.ApiReq;
import com.kalatsky.searchphotos.models.PhotoLocation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface FlickrAPI {

  @GET("rest")
  Observable<ApiReq> searchPhotosRX(@Query("method") String method, @Query("api_key") String api_key, @Query("text") String text, @Query("has_geo") int geo, @Query("format") String format, @Query("nojsoncallback") int calback);

  @GET("rest")
  Observable<PhotoLocation> getPhotoLocationRX(@Query("method") String method, @Query("api_key") String api_key, @Query("photo_id") long id, @Query("format") String format, @Query("nojsoncallback") int calback);

}
