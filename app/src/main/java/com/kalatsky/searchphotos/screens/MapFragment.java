package com.kalatsky.searchphotos.screens;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kalatsky.searchphotos.R;
import com.kalatsky.searchphotos.models.Photo;


public class MapFragment extends Fragment {


  private MapView mMapView;
  private GoogleMap googleMap;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_map, container, false);

    mMapView = (MapView) rootView.findViewById(R.id.mapView);
    mMapView.onCreate(savedInstanceState);
    mMapView.onResume(); // needed to get the map to display immediately

    try {
      MapsInitializer.initialize(getActivity().getApplicationContext());
    } catch (Exception e) {
      e.printStackTrace();
    }

    mMapView.getMapAsync(new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap mMap) {
          googleMap = mMap;
          if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
          }
          googleMap.setMyLocationEnabled(true);
          googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
    });
    return rootView;
  }

  public void setMarker(Photo photo) {
    if (googleMap != null) {
      googleMap.addMarker(new MarkerOptions()
              .position(new LatLng(photo.getLocation().getLatitude(), photo.getLocation().getLongitude()))
              .title(photo.getTitle()));
    }
  }

  public void clearMarkers() {
    if (googleMap != null) {
      googleMap.clear();
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    mMapView.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
    mMapView.onPause();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mMapView.onDestroy();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mMapView.onLowMemory();
  }

}
