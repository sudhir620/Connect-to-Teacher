package com.skcoder.ctcenter.ui.ebooks;

public class model {
    String Title,url;

    public model() {
    }

    public model(String name, String url) {
        this.Title = name;
        this.url = url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
