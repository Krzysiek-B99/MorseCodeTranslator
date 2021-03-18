package com.example.morse_translator_online.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {
    void uploadFile(MultipartFile file) throws IOException;
}
