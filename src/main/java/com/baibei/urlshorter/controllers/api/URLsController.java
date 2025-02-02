package com.baibei.urlshorter.controllers.api;

import com.baibei.urlshorter.models.URL;
import com.baibei.urlshorter.repositories.URLRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/url")
public class URLsController {

    private final URLRepository urlRepository;

    public URLsController(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @GetMapping
    public List<URL> getURLs() {
        return (List<URL>) urlRepository.findAll();
    }

    @GetMapping("/{id}")
    public URL getURL(@PathVariable long id) {
        return urlRepository.findById(id);
    }

    @PostMapping
    public URL createURL(@RequestBody URL url) {
        return urlRepository.save(url);
    }

    @PatchMapping
    public URL updateURL(@RequestBody URL url) {
        return urlRepository.save(url);
    }
}
