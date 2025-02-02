package com.baibei.urlshorter.services;

import com.baibei.urlshorter.components.Base62;
import com.baibei.urlshorter.models.URL;
import com.baibei.urlshorter.repositories.URLRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlService {

    private static int SHORT_ID_LENGTH = 6;
    private static final int MAX_ATTEMPTS = 10;

    private final URLRepository urlRepository;
    private final Base62 base62;
    private final Random random = new Random();

    public UrlService(URLRepository urlRepository,
                      Base62 base62) {
        this.urlRepository = urlRepository;
        this.base62 = base62;
    }

    public URL make(String rawUrl) {
        return urlRepository.findByOriginalUrl(rawUrl).orElseGet(() -> {
            String shortId = generateUniqueShortId();
            URL newUrl = new URL(rawUrl, shortId);
            return urlRepository.save(newUrl);
        });
    }

    private String generateUniqueShortId() {
        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            long randomNumber = generateRandomNumber();
            String shortId = base62.encode(randomNumber);

            if (!urlRepository.existsByShortId(shortId)) {
                return shortId;
            }
        }

        SHORT_ID_LENGTH++;
        return generateUniqueShortId();
    }

    private long generateRandomNumber() {
        long min = (long) Math.pow(62, UrlService.SHORT_ID_LENGTH - 1);
        long max = (long) Math.pow(62, UrlService.SHORT_ID_LENGTH) - 1;
        return min + Math.abs(random.nextLong()) % (max - min + 1);
    }

    public String getFullUrl(String shortId) {
        URL url = urlRepository.findByShortId(shortId).orElse(null);
        if (url == null) {
            return "URL not found";
        } else {
            return url.getOriginalUrl();
        }
    }
}
