package com.example.morse_translator_online.controller;


import com.example.morse_translator_online.service.MorseTranslatorService;
import com.example.morse_translator_online.service.MorseTranslatorServiceImpl;
import com.example.morse_translator_online.service.UploadFileService;
import com.example.morse_translator_online.service.UploadFileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

@Controller
public class UploadFileController {

    private final UploadFileService uploadFileService;
    private final MorseTranslatorService morseTranslatorService;

    public UploadFileController(UploadFileService uploadFileService, MorseTranslatorService morseTranslatorService) throws IOException {
        this.uploadFileService = uploadFileService;
        this.morseTranslatorService = morseTranslatorService;
    }


    @GetMapping("/hello")
    public String startPage(){
        return "uploader";
    }

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file){
        try {
            try {
                uploadFileService.uploadFile(file);
            }
            catch (IOException e){
                return ResponseEntity.ok().build();
            }
            FileWriter fileWriter = new FileWriter("D:\\JAVA_projects\\morse_translator_online\\src\\main\\resources\\templates\\output.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(morseTranslatorService.morseConvert(Paths.get("D:\\JAVA_projects\\morse_translator_online\\src\\main\\resources\\templates\\input.txt"),1)); //1:en->morse   else    morse->en
            printWriter.close();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();//("File uploaded");
    }

}
