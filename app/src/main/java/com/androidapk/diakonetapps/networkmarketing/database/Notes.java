package com.androidapk.diakonetapps.networkmarketing.database;

public class Notes {

    private String topic;
    private String note;
    private int image;

    public Notes(){}

    public Notes(String topic, String note, int image) {
        this.topic = topic;
        this.note = note;
        this.image = image;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
