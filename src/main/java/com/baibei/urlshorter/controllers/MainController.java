package com.baibei.urlshorter.controllers;

import com.baibei.urlshorter.components.ShortUrlBase;
import com.baibei.urlshorter.models.URL;
import com.baibei.urlshorter.services.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private final UrlService urlService;
    private final ShortUrlBase shortUrlBase;

    public MainController(UrlService urlService,
                          ShortUrlBase shortUrlBase) {
        this.urlService = urlService;
        this.shortUrlBase = shortUrlBase;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping
    public String shorter(String rawUrl, Model model) {
        URL url = urlService.make(rawUrl);
        model.addAttribute("url", rawUrl);
        model.addAttribute("shorter", shortUrlBase.getBaseUrl() + "/" + url.getShortId());
        return "index";
    }
}
