package me.gt.shorturl.service;

public interface RedisService {

    void saveShortUrl(String shortUrl, String originalUrl, long timeout);

    String getShortUrl(String longUrl);

    String getOriginaUrl(String shortUrl);

    boolean isExistShortUrl(String shortUrl);

    void addExpireTime(String url, long timeout);

    void deleteShortUrl(String shortUrl);
}
