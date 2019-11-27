package com.stackextend.websocketbackendexample.controller;

import com.stackextend.websocketbackendexample.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@CrossOrigin("*")
@RestController
public class NotificationController {
    private final SimpMessagingTemplate template;

    private final FileService fileService;
    @Autowired
    NotificationController(SimpMessagingTemplate template, FileService fileService){
        this.template = template;
        this.fileService = fileService;
    }
//
//    @MessageMapping("/send/message")
//    public void onReceivedMessage(@Payload String message){
//
//        this.template.convertAndSend("/chat",  message);
//    }
    @PostMapping(value = "/api/files")
    public void handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam String fromid,@RequestParam String toid,@RequestParam String filename,@RequestParam String ext) throws IOException {
        this.fileService.storeFile(file,fromid,toid,filename,ext);

    }
    @GetMapping(value = "api/download")
    public void getfilepath()
    {
        this.fileService.getfilepath();
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
