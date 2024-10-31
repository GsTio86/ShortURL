package me.gt.shorturl.service.impl;

import jakarta.annotation.PostConstruct;
import me.gt.shorturl.mapper.ShortUrlMapper;
import me.gt.shorturl.model.ShortUrlInfo;
import me.gt.shorturl.service.RedisService;
import me.gt.shorturl.service.ShortUrlService;
import me.gt.shorturl.util.URLShortUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    private ShortUrlMapper shortUrlMapper;

    @Autowired
    private RedisService redisService;

    @Value("${shorturl.timeout}")
    private long timeout;

    private static final Logger logger = LoggerFactory.getLogger("ShortUrlService");

    private static final String DUPLICATE_CHAR = "*";

    @PostConstruct
    public void init() {
        List<ShortUrlInfo> shortUrls = shortUrlMapper.getShortUrls();
        for (ShortUrlInfo shortUrlInfo : shortUrls) {
            redisService.saveShortUrl(shortUrlInfo.getShortUrl(), shortUrlInfo.getOriginalUrl(), timeout);
        }
    }

    @Override
    public String createShortUrl(String longUrl, String originalUrl) {
        String shortUrl = redisService.getShortUrl(longUrl);
        if (shortUrl != null) { // 代表此網址在緩存時間內 產生過短網址
            redisService.addExpireTime(shortUrl, timeout); // 更新緩存時間
            return shortUrl;
        }
        shortUrl = URLShortUtils.generateShortUrl(longUrl); // 產生短網址
        if (redisService.isExistShortUrl(shortUrl)) { // 判斷是否存在
            String cacheLongUrl = redisService.getOriginaUrl(shortUrl); // 透過短網址取得原始網址
            if (cacheLongUrl != null && cacheLongUrl.equals(longUrl)) { // 判斷此網址是否和現在的網址相同
                redisService.saveShortUrl(shortUrl, longUrl, timeout); // 更新緩存時間
                return shortUrl;
            } // 若存在，加上後綴 然後重新產生短網址
            longUrl = originalUrl + DUPLICATE_CHAR;
            shortUrl = createShortUrl(URLShortUtils.generateShortUrl(longUrl), originalUrl);
        }
        ShortUrlInfo shortUrlInfo = ShortUrlInfo.builder() // 建立短網址資料
                .shortUrl(shortUrl)
                .originalUrl(originalUrl)
                .build();
        shortUrlMapper.createShortUrl(shortUrlInfo); // 存到資料庫
        redisService.saveShortUrl(shortUrl, longUrl, timeout); // 放入緩存
        logger.info("建立短網址: {} -> {}", shortUrl, originalUrl);
        return shortUrl;
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        String longUrl = redisService.getShortUrl(shortUrl);
        if (longUrl != null) { // 判斷是否有緩存
            redisService.addExpireTime(shortUrl, timeout); // 更新緩存時間
            return longUrl;
        }
        ShortUrlInfo shortUrlInfo = shortUrlMapper.getShortUrl(shortUrl);
        if (shortUrlInfo == null) {
            return null;
        }
        longUrl = shortUrlInfo.getOriginalUrl();
        redisService.saveShortUrl(shortUrl, longUrl, timeout); // 放入緩存
        return longUrl;
    }

    @Override
    public ShortUrlInfo getShortUrlInfo(String shortUrl) {
        return shortUrlMapper.getShortUrl(shortUrl);
    }

    @Override
    public List<ShortUrlInfo> getShortUrls() {
        return shortUrlMapper.getShortUrls();
    }

    @Override
    public void addClickCount(String shortUrl) {
        shortUrlMapper.addClickCount(shortUrl);
    }

    @Override
    public void deleteShortUrl(String shortUrl) {
        redisService.deleteShortUrl(shortUrl); // 刪除緩存
        shortUrlMapper.deleteShortUrl(shortUrl);
        logger.info("刪除短網址: {}", shortUrl);
    }
}
