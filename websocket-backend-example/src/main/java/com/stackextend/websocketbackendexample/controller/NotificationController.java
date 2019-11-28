package com.stackextend.websocketbackendexample.controller;

import com.stackextend.websocketbackendexample.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin("*")
@RestController
public class NotificationController {
    private final SimpMessagingTemplate template;
    private static final String FILE_DIRECTORY = "/home/cgi/trial";

    private final FileService fileService;
    @Autowired
    NotificationController(SimpMessagingTemplate template, FileService fileService){
        this.template = template;
        this.fileService = fileService;
    }

    @MessageMapping("/send/message")
    public void onReceivedMessage(@Payload String message){

        this.template.convertAndSend("/chat",  message);
    }
    @PostMapping(value = "/api/files")
    public void handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam String fromid,@RequestParam String toid,@RequestParam String filename,@RequestParam String ext,@RequestParam String lastext) throws IOException {
        this.fileService.storeFile(file,fromid,toid,filename,ext,lastext);

    }

    @GetMapping(value = "/api/download/{filename}/{lastext}")
    public Resource getFileFromFileSystem(@PathVariable String filename,@PathVariable String lastext, HttpServletResponse response) {
        System.out.println("inside controller");
        return fileService.getFileSystem(filename,lastext, response);

    }
//    @GetMapping(value = "/api/download/{filename}/{lastext}")
//    public InputStreamResource FileSystemResource (HttpServletResponse response,@PathVariable String filename,@PathVariable String lastext) throws IOException {
//        response.setContentType("application/pdf");
//        String FILE_PATH=FILE_DIRECTORY+'/'+filename+lastext;
//        response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(FILE_PATH));
//        return resource;
//    }


//    public Resource getfilepath(@RequestParam String filename)
//    {
//        String filepathfromMongo1=this.fileService.getfilepath(fromid,toid,filename);
//        Path p1 = Paths.get(filepathfromMongo1);
//        File f = new File(filepathfromMongo1);
//        String fname=f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\")+1);
//        return fileService.getFileSystem(fname);
//        System.out.println(fname);
//
//        System.out.print(filepathfromMongo1);
//
//    }


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
