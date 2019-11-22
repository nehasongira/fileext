package com.stackextend.websocketbackendexample.model;

public class Notifications {

    public Notifications(int count, String name, String content) {
        this.count = count;
        this.name = name;
        this.content = content;
    }

    private int count;
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
