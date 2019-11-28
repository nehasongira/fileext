package com.stackextend.websocketbackendexample.services;

import com.stackextend.websocketbackendexample.FileRepository;
import com.stackextend.websocketbackendexample.model.Filess;
import org.slf4j.Logger;
import java.io.File;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletResponse;
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

    public void storeFile(MultipartFile file,String fromid,String toid,String filename, String ext,String lastext) throws IOException {
        String name = fromid+ "_" + toid+"_"+file.getOriginalFilename();
        Path filePath = Paths.get(FILE_DIRECTORY + "/" + name);
        System.out.println(fromid);
        System.out.println("hello");
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        String path=filePath.toString();
        Filess files = new Filess();
        files.setFilePath(path);
        files.setFromid(fromid);
        files.setToid(toid);
        files.setFilename(name);
        files.setExt(ext);
        files.setLastext(lastext);
        this.fileRepository.save(files);
    }

    public Resource getFileSystem(String filename,HttpServletResponse response) {
        System.out.println("inside controller-service");
        return getResource(filename, response);
    }

    private Resource getResource(String filename ,HttpServletResponse response) {

        System.out.println(filename);

        String filepathfromMongo=this.fileRepository.findpath(filename).getFilePath();
        String fileextfromMongo=this.fileRepository.findext(filename).getExt();


        response.setContentType(fileextfromMongo);
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setHeader("fileName", filename);
        System.out.println();
        Resource resource = null;

       resource = new FileSystemResource(filepathfromMongo);
        System.out.println(resource);
        System.out.println("inside controller resource");
        //return new FileSystemResource(file);
       return resource;
    }
//    public String getfilepath(String fromid,String toid,String filename)
//    {
//        String filepathfromMongo=this.fileRepository.findPathWithFromidAndToid(fromid, toid, filename).getFilePath();
//        System.out.println(filepathfromMongo);
//        return filepathfromMongo;
//
//
//    }
//    public Resource getFileSystem(String filename, HttpServletResponse response) {
//        return getResource(filename, response, ResourceType.FILE_SYSTEM);
//    }



}
//       //String filepathfromMongo="abc_efg_18augmeal.pdf";
//       String FILE_PATH=FILE_DIRECTORY+'/'+filename+lastext;
//       System.out.println(FILE_PATH);
//        //Path pa=Paths.get(FILE_PATH);
// File file = new File(FILE_PATH);
//File file = new File("/home/cgi/trial/abc_efg_3ac.pdf");