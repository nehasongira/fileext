package com.stackextend.websocketbackendexample.controller;


import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class Filecontroller {

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String upload(HttpServletRequest request) {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                // Inform user about invalid request
                String response="not possible";
               // Response<String> responseObject = new Response<String>(false, "Not a multipart request.", "");
                return response;
            }

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload();

            // Parse the request
            FileItemIterator iter = upload.getItemIterator(request);
            while (iter.hasNext()) {

                FileItemStream item = iter.next();
                String name = item.getFieldName();
                InputStream stream = item.openStream();
                if (!item.isFormField()) {
                    String filename = item.getName();
                    // Process the input stream
                    OutputStream out = new FileOutputStream(filename);
                    IOUtils.copy(stream, out);
                    stream.close();
                    out.close();
                }
            }
        } catch (FileUploadException e) {
            return "file upload error";
            //return new Response<String>(false, "File upload error", e.toString());
        } catch (IOException e) {
            return "internal server io error";
            //return new Response<String>(false, "Internal server IO error", e.toString());
        }
           return "success";
        //return new Response<String>(true, "Success", "");
    }

    @RequestMapping(value = "/uploader", method = RequestMethod.GET)
    public ModelAndView uploaderPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("uploader");
        return model;
    }

}
