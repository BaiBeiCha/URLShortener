package com.baibei.urlshorter.components;

import com.baibei.urlshorter.services.FilesService;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlBase {

    private static final String path = "baseurl.txt";
    private final String baseUrl;

    public ShortUrlBase(FilesService filesService) {
        if (filesService.exists(path)) {
            this.baseUrl = filesService.readFile(path).get(0).trim();
        } else {
            filesService.createFile(path);
            this.baseUrl = "localhost:8080";
            filesService.writeFile(path, baseUrl, false);
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
