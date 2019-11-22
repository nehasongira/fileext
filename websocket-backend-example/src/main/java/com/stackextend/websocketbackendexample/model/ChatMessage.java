package com.stackextend.websocketbackendexample.model;

public class ChatMessage {

    //private MessageType type;
    private String sender;
    private String content;


//    public enum MessageType {
//        CHAT,
//        JOIN,
//        LEAVE
//    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}

