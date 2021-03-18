package com.example.morse_translator_online.service;

import java.nio.file.Path;
import java.util.Map;

public interface MorseTranslatorService {

    public Map<String,String> getMorseCodeMap(int option);
    public String fileToString(Path file);
    String morseConvert(Path file,int option);
}
