package com.stackextend.websocketbackendexample.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class File {

    public File(String filePath, String fromid, String toid, String filename, String ext) {
        this.filePath = filePath;
        this.fromid = fromid;
        this.toid = toid;
        this.filename = filename;
        this.ext = ext;
    }

    @Id
    private String filePath;
    private String fromid;
    private String toid;
    private String filename;
    private String ext;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public File() {
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
