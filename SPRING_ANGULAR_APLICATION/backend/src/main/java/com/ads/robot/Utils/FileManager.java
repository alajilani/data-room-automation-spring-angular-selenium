package com.ads.robot.Utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class FileManager {

    public void writeToFile(String path,String text){
        File file = new File(path);
        try {
            file.createNewFile();
            System.out.println(path);
            System.out.println(text);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void replaceInFile(String regex,String replacementText,String filePath){
        try {
            File file = new File(filePath);
            String content = Files.readString(file.toPath());
            content=content.replace(regex,replacementText);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void makeDirectoryIfItDoesntExist(String path){
        File directory = new File(path);
        if(!directory.exists()){
            directory.mkdirs();
        }
    }
}
