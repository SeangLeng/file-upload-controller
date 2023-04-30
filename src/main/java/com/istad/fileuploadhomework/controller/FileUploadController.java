package com.istad.fileuploadhomework.controller;

import com.istad.fileuploadhomework.modelResponse.FileRespone;
import com.istad.fileuploadhomework.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    @PostMapping("/upload") 
    public ResponseEntity<FileRespone> fileUpload(@RequestParam("image") MultipartFile image){
        String filename = null;
        try {
            filename = this.fileService.uploadImage(path, image);

        } catch (IOException e) {
            return new ResponseEntity<>(new FileRespone(null, "images is not upload !!!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new FileRespone(filename, "images is successfully upload to server!!"), HttpStatus.OK);
    }
}
