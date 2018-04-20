package com.kalatsky.searchphotos.models;

public class ApiReq {

  private Photos photos;
  private String stat;

  public String getStat() {
    return stat;
  }

  public void setStat(String stat) {
    this.stat = stat;
  }

  public Photos getPhotos() {
    return photos;
  }

  @Override
  public String toString() {
    return "ApiReq{" +
            "photos=" + photos +
            '}';
  }

  public void setPhotos(Photos photos) {
    this.photos = photos;
  }
}
