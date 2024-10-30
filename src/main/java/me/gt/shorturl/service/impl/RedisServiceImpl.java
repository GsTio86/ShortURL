package me.gt.shorturl.service.impl;

import me.gt.shorturl.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String SHORT_URL_SET = "short_url_set";

    @Override
    public void saveShortUrl(String shortUrl, String originalUrl, long timeout) {
        redisTemplate.opsForValue().set(shortUrl, originalUrl, timeout, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(originalUrl, shortUrl, timeout, TimeUnit.MINUTES);
        redisTemplate.opsForSet().add(SHORT_URL_SET, shortUrl);
    }

    @Override
    public String getShortUrl(String longUrl) {
        return redisTemplate.opsForValue().get(longUrl);
    }

    @Override
    public String getOriginaUrl(String shortUrl) {
        return redisTemplate.opsForValue().get(shortUrl);
    }

    @Override
    public boolean isExistShortUrl(String shortUrl) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(SHORT_URL_SET, shortUrl));
    }

    @Override
    public void addExpireTime(String url, long timeout) {
        redisTemplate.expire(url, timeout, TimeUnit.MINUTES);
    }

    @Override
    public void deleteShortUrl(String shortUrl) {
        String originalUrl = getOriginaUrl(shortUrl);
        if (originalUrl != null) {
            redisTemplate.delete(originalUrl); // 刪除原始網址
        }
        redisTemplate.delete(shortUrl); // 刪除原始網址
        redisTemplate.opsForSet().remove(SHORT_URL_SET, shortUrl); // 刪除集合中的短網址
    }


}
