package me.gt.shorturl.service;

import me.gt.shorturl.model.ShortUrlInfo;

import java.util.List;

public interface ShortUrlService {

    String createShortUrl(String longUrl, String originalUrl);

    String getOriginalUrl(String shortUrl);

    ShortUrlInfo getShortUrlInfo(String shortUrl);

    List<ShortUrlInfo> getShortUrls();

    void addClickCount(String shortUrl);

    void deleteShortUrl(String shortUrl);
}
