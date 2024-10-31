package me.gt.shorturl.service.impl;

import me.gt.shorturl.model.ShortUrlInfo;
import me.gt.shorturl.service.ShortUrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShortUrlServiceImplTest {

    @Autowired
    private ShortUrlService service;

    @Test
    void createShortUrl() {
        String shortUrl = service.createShortUrl("https://www.google.com", "https://www.google.com");
        System.out.println(shortUrl);
    }

    @Test
    void getOriginalUrl() {
        System.out.println(service.getOriginalUrl("S12nyC"));
    }

    @Test
    void getShortUrls() {
        for (ShortUrlInfo shortUrlInfo : service.getShortUrls()) {
            System.out.println(shortUrlInfo);
        }
    }

    @Test
    void addClickCount() {
        service.addClickCount("S12nyC");
        ShortUrlInfo shortUrlInfo = service.getShortUrlInfo("S12nyC");
        if (shortUrlInfo != null) {
            System.out.println(shortUrlInfo);
        }
    }

    @Test
    void deleteShortUrl() {
        service.deleteShortUrl("S12nyC");
    }
}