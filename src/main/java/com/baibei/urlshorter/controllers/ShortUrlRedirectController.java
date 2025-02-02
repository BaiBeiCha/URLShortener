package com.baibei.urlshorter.controllers;

import com.baibei.urlshorter.services.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShortUrlRedirectController {

    private final UrlService urlService;

    public ShortUrlRedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortId}")
    public String redirectToShortUrl(@PathVariable String shortId) {
        String fullUrl = urlService.getFullUrl(shortId);
        if (fullUrl != null && !fullUrl.isEmpty()) {
            return "redirect:" + fullUrl;
        }
        return "redirect:/error/404";
    }
}
