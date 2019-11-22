package com.stackextend.websocketbackendexample.controller;

import com.stackextend.websocketbackendexample.model.ChatMessage;
import com.stackextend.websocketbackendexample.model.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class NotificationController {
    private final SimpMessagingTemplate template;

    @Autowired
    NotificationController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/send/message")
    public void onReceivedMessage(@Payload String message){
        this.template.convertAndSend("/chat",  message);
    }

//////////////////////////////////////////////////////////////////////
//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
//        return chatMessage;
//    }
//
//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(@Payload ChatMessage chatMessage,
//                               SimpMessageHeaderAccessor headerAccessor) {
//        // Add username in web socket session
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }
///////////////////////////////////////////////////////////////////////////
//    @Autowired
//    private SimpMessagingTemplate template;
//
//    private Notifications notifications = new Notifications();
//
//    @GetMapping("/notify")
//    public String getNotification() {
//
//        notifications.increment();
//
//        template.convertAndSend("/topic/notification", notifications);
//
//        return "Notifications successfully sent to Angular !";
//    }
}
