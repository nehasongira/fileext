//package com.stackextend.websocketbackendexample.controller;
//
//import com.stackextend.websocketbackendexample.model.Notifications;
//import com.stackextend.websocketbackendexample.services.FileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.management.Notification;
//import java.io.IOException;
//@CrossOrigin("*")
//@RestController
//public class controller {
//    private final FileService fileService;
//
//    @Autowired
//    public controller(FileService fileService) {
//        this.fileService = fileService;
//    }
//
//    @PostMapping(value = "/api/files")
//    public void handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam String fromid,@RequestParam String toid,@RequestParam String filename,@RequestParam String ext) throws IOException {
//        fileService.storeFile(file,fromid,toid,filename,ext);
//    }
//
//
//
//}
