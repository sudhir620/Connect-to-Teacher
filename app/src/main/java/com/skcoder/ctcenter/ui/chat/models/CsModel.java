package com.skcoder.ctcenter.ui.chat.models;

public class CsModel {
    String message,sender_name, imgUrl;

    public CsModel() {
    }

    public CsModel(String message, String sender_name, String imgUrl) {
        this.message = message;
        this.sender_name = sender_name;
        this.imgUrl = imgUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
