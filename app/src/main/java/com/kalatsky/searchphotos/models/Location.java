package com.kalatsky.searchphotos.models;

public class Location {

  private double latitude;
  private double longitude;
  private int accuracy;
  private int context;

  @Override
  public String toString() {
    return "Location{" +
            "latitude=" + latitude +
            ", longitude=" + longitude +
            ", accuracy=" + accuracy +
            ", context=" + context +
            '}';
  }

  public double getLatitude() {
    return latitude;
    }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public int getAccuracy() {
    return accuracy;
  }

  public void setAccuracy(int accuracy) {
    this.accuracy = accuracy;
  }

  public int getContext() {
    return context;
  }

  public void setContext(int context) {
    this.context = context;
  }
}
