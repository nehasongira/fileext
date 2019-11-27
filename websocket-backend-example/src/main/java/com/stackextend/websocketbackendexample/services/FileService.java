package com.stackextend.websocketbackendexample.services;

import com.stackextend.websocketbackendexample.FileRepository;
import com.stackextend.websocketbackendexample.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class FileService {


    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private FileRepository fileRepository;
    private static final String FILE_DIRECTORY = "/home/cgi/trial";

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void storeFile(MultipartFile file,String fromid,String toid,String filename, String ext) throws IOException {
        Path filePath = Paths.get(FILE_DIRECTORY + "/" + file.getOriginalFilename());
        System.out.println(fromid);
        System.out.println("hello");
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        String path=filePath.toString();
        File files = new File();
        files.setFilePath(path);
        files.setFromid(fromid);
        files.setToid(toid);
        files.setFilename(filename);
        files.setExt(ext);
        this.fileRepository.save(files);
    }
    public void getfilepath()
    {
        File filespath=new File();

    }



}
