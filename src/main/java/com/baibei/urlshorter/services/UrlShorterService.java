package com.baibei.urlshorter.services;

import com.baibei.urlshorter.components.Base62;
import com.baibei.urlshorter.components.ShortUrlBase;
import org.springframework.stereotype.Service;

@Service
public class UrlShorterService {

    private final Base62 base62;

    public UrlShorterService(Base62 base62) {
        this.base62 = base62;
    }

    public String shorter(long id) {
        return base62.encode(id);
    }
}
