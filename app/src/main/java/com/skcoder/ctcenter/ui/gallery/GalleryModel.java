package com.skcoder.ctcenter.ui.gallery;

public class GalleryModel {
    String ImgURL;

    public GalleryModel() {
    }

    public GalleryModel(String imgURL) {
        ImgURL = imgURL;
    }

    public String getImgURL() {
        return ImgURL;
    }

    public void setImgURL(String imgURL) {
        ImgURL = imgURL;
    }
}
