package com.kalatsky.searchphotos.screens;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalatsky.searchphotos.R;
import com.kalatsky.searchphotos.adapter.ListPhotosRVAdapter;
import com.kalatsky.searchphotos.models.Photo;

import java.util.List;


public class ListPhotoFragment extends Fragment {

  private RecyclerView recyclerView;
  private ListPhotosRVAdapter adapter;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_list_photo, container, false);

    recyclerView = (RecyclerView) view.findViewById(R.id.list_photos);

    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

    adapter = new ListPhotosRVAdapter();

    recyclerView.setAdapter(adapter);

    return view;
  }

  public void setElementInList(List<Photo> photos) {
    adapter.setNewList(photos);
  }
}
