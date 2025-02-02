package com.baibei.urlshorter.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FilesService {

    public boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }

    public void createFile(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Can't create file");
        }
    }

    public void createFile(File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Can't create file");
        }
    }

    public void writeFile(String path, String content, boolean append) {
        try(FileWriter writer = new FileWriter(path, append))
        {
            writer.write(content + "\n");
            writer.flush();
        }
        catch(Exception e){
            System.out.println("Can't write file");
        }
    }

    public void writeFile(String path, String[] content, boolean append) {
        try(FileWriter writer = new FileWriter(path, append))
        {
            for(String s : content) {
                writer.write(s + "\n");
            }
            writer.flush();
        }
        catch(Exception e){
            System.out.println("Can't write file");
        }
    }

    public List<String> readFile(String path) {
        List<String> list = new ArrayList<>();

        try(Scanner sc = new Scanner(new File(path))) {
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public String readFileAsString(String path) {
        List<String> list = readFile(path);

        StringBuilder sb = new StringBuilder();
        for(String s : list) {
            sb.append(s).append("\n");
        }

        return sb.toString();
    }

    public void clearFile(String path) {
        writeFile(path, "", false);
    }

    public void createDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
