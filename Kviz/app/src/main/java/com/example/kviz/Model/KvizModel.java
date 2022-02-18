package com.example.kviz.Model;

import java.lang.annotation.Documented;
import java.lang.reflect.Constructor;

public class KvizModel {

    String kvizid;
    String image, kvizname, visiblity, description;
    Long question;

    //
    public KvizModel() {

    }

    public KvizModel(String kvizid, String image, String visiblity, String description, Long question) {
        this.kvizid = kvizid;
        this.image = image;
        this.kvizname = kvizname;
        this.visiblity = visiblity;
        this.description = description;
        this.question = question;
    }

    public String getKvizid() {
        return kvizid;
    }

    public void setKvizid(String kvizid) {
        this.kvizid = kvizid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKvizname() {
        return kvizname;
    }

    public void setKvizname(String kvizname) {
        this.kvizname = kvizname;
    }

    public String getVisiblity() {
        return visiblity;
    }

    public void setVisiblity(String visiblity) {
        this.visiblity = visiblity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuestion() {
        return question;
    }

    public void setQuestion(Long question) {
        this.question = question;
    }
}
