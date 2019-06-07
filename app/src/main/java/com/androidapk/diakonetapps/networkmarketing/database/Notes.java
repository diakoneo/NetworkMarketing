package com.androidapk.diakonetapps.networkmarketing.database;

public class Notes {

    private String topic;
    private String file;
    private String note;
    private String image;

    public Notes(){}

    public Notes(String topic, String file, String note, String image) {
        this.topic = topic;
        this.file = file;
        this.note = note;
        this.image = image;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
