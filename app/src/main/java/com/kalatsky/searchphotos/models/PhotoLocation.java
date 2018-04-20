package com.kalatsky.searchphotos.models;

public class PhotoLocation {

  private Photo photo;
  private String stat;

  @Override
  public String toString() {
    return "PhotoLocation{" +
                "photo=" + photo +
                ", stat='" + stat + '\'' +
                '}';
  }

  public String getStat() {
    return stat;
  }

  public void setStat(String stat) {
    this.stat = stat;
  }

  public Photo getPhoto() {
    return photo;
  }

  public void setPhoto(Photo photo) {
    this.photo = photo;
  }
}
