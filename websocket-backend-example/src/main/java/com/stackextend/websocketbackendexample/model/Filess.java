package com.stackextend.websocketbackendexample.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Filess {


    public Filess(String filePath, String fromid, String toid, String filename, String ext, String lastext) {
        this.filePath = filePath;
        this.fromid = fromid;
        this.toid = toid;
        this.filename = filename;
        this.ext = ext;
        this.lastext = lastext;
    }

    @Id
    private String filePath;
    private String fromid;
    private String toid;
    private String filename;
    private String ext;
    private String lastext;

    public String getLastext() {
        return lastext;
    }

    public void setLastext(String lastext) {
        this.lastext = lastext;
    }



    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public Filess() {
    }


    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }


}
