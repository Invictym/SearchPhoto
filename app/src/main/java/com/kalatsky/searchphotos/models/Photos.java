package com.kalatsky.searchphotos.models;

import java.util.List;

public class Photos {


  private String page;
  private String pages;
  private String perpage;
  private String total;
  private List<Photo> photo;

  @Override
  public String toString() {
    return "Photos{" +
            "photo=" + photo +
            ", page=" + page +
            ", pages=" + pages +
            ", perpage=" + perpage +
            ", total=" + total +
            '}';
  }

  public Photos(String page, String pages, String perpage, String total, List<Photo> photo) {
    this.page = page;
    this.pages = pages;
    this.perpage = perpage;
    this.total = total;
    this.photo = photo;
  }

  public String getPage() {
    return page;
  }

  public void setPage(String page) {
    this.page = page;
  }

  public String getPages() {
    return pages;
  }

  public void setPages(String pages) {
    this.pages = pages;
  }

  public String getPerpage() {
    return perpage;
  }

  public void setPerpage(String perpage) {
    this.perpage = perpage;
  }

  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

  public List<Photo> getPhoto() {
    return photo;
  }

  public void setPhoto(List<Photo> photo) {
    this.photo = photo;
  }

}
