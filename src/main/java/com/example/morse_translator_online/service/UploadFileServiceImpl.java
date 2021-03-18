package com.example.morse_translator_online.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileServiceImpl implements UploadFileService {


    @Override
    public void uploadFile(MultipartFile multipartFile) throws IOException{
        String fileName = multipartFile.getOriginalFilename();
        multipartFile.transferTo(new File("D:\\JAVA_projects\\morse_translator_online\\src\\main\\resources\\input\\input.txt"));
    }
}
