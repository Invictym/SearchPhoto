package com.kalatsky.searchphotos.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalatsky.searchphotos.R;
import com.kalatsky.searchphotos.logic.CreaterPhotoURL;
import com.kalatsky.searchphotos.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListPhotosRVAdapter extends RecyclerView.Adapter<ListPhotosRVAdapter.PhotoViewHolder> {

  private static volatile List<Photo> photos = new ArrayList<>();

  public ListPhotosRVAdapter() {

  }
  public void addItem(Photo photo) {
      photos.add(photo);
      notifyDataSetChanged();
  }

  public void setNewList(List<Photo> list) {
    photos = new ArrayList<>();
    photos.addAll(list);
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photos_list_element, parent, false);
    return new PhotoViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
    holder.title.setText(photos.get(position).getTitle());
    Picasso.with(holder.photo.getContext())
            .load(CreaterPhotoURL.createURLFromPhoto(photos.get(position)))
            .into(holder.photo);
  }

  @Override
  public int getItemCount() {
    return photos.size();
  }

  @Override
  public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }

  public class PhotoViewHolder extends RecyclerView.ViewHolder {
    ImageView photo;
    TextView title;

    public PhotoViewHolder(View itemView) {
      super(itemView);
      photo = (ImageView) itemView.findViewById(R.id.list_photos_item_image);
      title = (TextView) itemView.findViewById(R.id.list_photos_item_title);
    }
  }
}
