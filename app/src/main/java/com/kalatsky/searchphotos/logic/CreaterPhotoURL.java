package com.kalatsky.searchphotos.logic;


import com.kalatsky.searchphotos.models.Photo;

public class CreaterPhotoURL {

  public static String createURLFromPhoto(Photo photo) {
    return "https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId() +"_" + photo.getSecret() + ".jpg";
  }
}
