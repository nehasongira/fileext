package com.stackextend.websocketbackendexample.model;

import org.springframework.web.multipart.MultipartFile;

public class Notifications {

    public Notifications(int count, String name, String content) {
        this.count = count;
        this.name = name;
        this.content = content;
    }
   private MultipartFile file;
    private int count;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    private String name;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



//    public Notifications(int count) {
//        this.count = count;
//    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment() {
        this.count++;
    }
}
