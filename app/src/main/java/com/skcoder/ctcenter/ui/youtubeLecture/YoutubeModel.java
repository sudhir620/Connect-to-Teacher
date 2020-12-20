package com.skcoder.ctcenter.ui.youtubeLecture;

public class YoutubeModel {
    String youtube_link, topic;

    public YoutubeModel() {
    }

    public YoutubeModel(String youtube_link, String topic) {
        this.youtube_link = youtube_link;
        this.topic = topic;
    }

    public String getYoutube_link() {
        return youtube_link;
    }

    public void setYoutube_link(String youtube_link) {
        this.youtube_link = youtube_link;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
