package com.kalatsky.searchphotos;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.kalatsky.searchphotos.adapter.SectionPageAdapter;
import com.kalatsky.searchphotos.api.RetrofitBilder;
import com.kalatsky.searchphotos.models.ApiReq;
import com.kalatsky.searchphotos.models.Photo;
import com.kalatsky.searchphotos.models.PhotoLocation;
import com.kalatsky.searchphotos.screens.ListPhotoFragment;
import com.kalatsky.searchphotos.screens.MapFragment;

import rx.Observable;
import rx.Subscription;

public class StartActivity extends AppCompatActivity {

  private SectionPageAdapter sectionPageAdapter;
  private ViewPager viewPager;
  private RetrofitBilder bilder;
  private MapFragment mapFragment;
  private ListPhotoFragment photosListFragment;

  private static ApiReq apiReq;
  private static Observable<PhotoLocation> observable = null;
  private static Observable<ApiReq> obserGettinPhoto = null;
  private static Subscription photoSub = null;
  private static Subscription photoLocationSub = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);
    sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
    viewPager = (ViewPager)findViewById(R.id.container);
    setupViewPager(viewPager);
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
    bilder = new RetrofitBilder();
    if (savedInstanceState == null) {
      searchPhotos("");
    }
  }

  private void searchPhotos(String text) {
    if (photoSub != null && !photoSub.isUnsubscribed()) {
      photoSub.unsubscribe();
      photoSub = null;
    }

    if (photoLocationSub != null && !photoLocationSub.isUnsubscribed()) {
      photoLocationSub.unsubscribe();
      photoLocationSub = null;
    }

    if (mapFragment != null) {
      mapFragment.clearMarkers();
    }
    obserGettinPhoto = RetrofitBilder.searchPhotosRX(text);
    photoSub = obserGettinPhoto
            .subscribe(this::resultSearchPhoto, this::erorr);
  }

  public void resultGettingPhotoLocation(PhotoLocation location) {

    Photo setPhoto = null;
    for (Photo photo : apiReq.getPhotos().getPhoto()) {
      if (photo.getId() == location.getPhoto().getId()) {
        setPhoto = photo;
        setPhoto.setLocation(location.getPhoto().getLocation());
        break;
      }
    }
    mapFragment.setMarker(setPhoto);
  }

  @Override
  protected void onDestroy() {
    if (photoSub != null && !photoSub.isUnsubscribed()) {
      photoSub.unsubscribe();
    }

    if (photoLocationSub != null && !photoLocationSub.isUnsubscribed()) {
      photoLocationSub.unsubscribe();
    }
    super.onDestroy();
  }

  public void resultSearchPhoto(ApiReq apiReq) {
    this.apiReq = apiReq;

    photosListFragment.setElementInList(apiReq.getPhotos().getPhoto());

    for (Photo photo : apiReq.getPhotos().getPhoto()) {
      if (observable != null) {
        observable = Observable.concat(bilder.getPhotoLocationRX(photo.getId()), observable);
      } else {
        observable = bilder.getPhotoLocationRX(photo.getId());
      }
    }
    photoLocationSub = observable.subscribe(this::resultGettingPhotoLocation, this::erorr);
  }

  public void erorr(Throwable t) {
      System.out.println("ERRR " + t);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.search_menu, menu);
    SearchManager searchManager =
            (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView =
            (SearchView) menu.findItem(R.id.menu_search).getActionView();
    searchView.setSearchableInfo(
            searchManager.getSearchableInfo(getComponentName()));
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        searchPhotos(query);
        System.out.println(query);
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        return false;
      }
    });
    return true;
  }

  private void setupViewPager(ViewPager viewPager) {
    photosListFragment = new ListPhotoFragment();
    mapFragment = new MapFragment();
    sectionPageAdapter.addFragment(photosListFragment, "List photo");
    sectionPageAdapter.addFragment(mapFragment, "Map");
    viewPager.setAdapter(sectionPageAdapter);
  }
}
