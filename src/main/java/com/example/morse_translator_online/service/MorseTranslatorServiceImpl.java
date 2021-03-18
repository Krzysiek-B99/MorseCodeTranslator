package com.example.morse_translator_online.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


@Service
public class MorseTranslatorServiceImpl implements MorseTranslatorService {

    public Map<String,String> getMorseCodeMap(int option) {
        Map<String,String> morse = new HashMap<>();
        List<String> morseList = new ArrayList<>();
        if(option==1) {
            try {
                Scanner scanner = new Scanner(new File("D:\\JAVA_projects\\morse_translator_online\\src\\main\\resources\\templates\\morse.txt"));
                while (scanner.hasNext()) {
                    morseList.add(scanner.next());
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
            for (int i = 0; i < morseList.size() - 1; i += 2) {
                morse.put(morseList.get(i), morseList.get(i + 1));
            }
            morse.put(" ","");
            return morse;
        }
        else{
            try {
                Scanner scanner = new Scanner(new File("D:\\JAVA_projects\\morse_translator_online\\src\\main\\resources\\templates\\morse.txt"));
                while (scanner.hasNext()) {
                    morseList.add(scanner.next());
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
            for (int i = 1; i <= morseList.size(); i += 2) {
                morse.put(morseList.get(i), morseList.get(i - 1));
            }
            morse.put(""," ");
            return morse;
        }
    }

    public String fileToString(Path file){
        Charset charset = StandardCharsets.US_ASCII;
        String line = null;
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            while ((line = reader.readLine()) != null) {
                text.append(line).append(" ");
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return String.valueOf(text);
    }

    public String morseConvert(Path file, int option){

        String text = fileToString(file);
        StringBuilder textResult = new StringBuilder();

        if (option == 1) {

            for (char letter : text.toUpperCase().toCharArray()) {
                textResult.append(getMorseCodeMap(1).get(String.valueOf(letter)));
                textResult.append(" ");
            }
        }
        else {
            StringBuilder word = new StringBuilder();
            for (char letter : text.toCharArray()) {
                if(letter!=' '){
                    word.append(letter);
                }
                else {
                    textResult.append(getMorseCodeMap(0).get(String.valueOf(word)));
                    word.setLength(0);
                }
            }
        }
        return String.valueOf(textResult);
    }

}

