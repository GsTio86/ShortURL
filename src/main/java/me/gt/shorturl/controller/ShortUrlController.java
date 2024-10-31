package me.gt.shorturl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.gt.shorturl.dto.UrlDto;
import me.gt.shorturl.model.ShortUrlInfo;
import me.gt.shorturl.service.ShortUrlService;
import me.gt.shorturl.util.URLShortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping
@Tag(name = "短網址 API", description = "管理短網址的操作")
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @Operation(summary = "產生短網址")
    @PostMapping(path = "/generate", consumes = "application/json")
    public ResponseEntity<String> generateShortUrlPost(@RequestBody UrlDto urlDto) {
        return generateShortUrl(urlDto.getUrl());
    }

    @Operation(summary = "取得所有短網址")
    @GetMapping("/getall")
    public ResponseEntity<List<ShortUrlInfo>> getAllShortUrls() {
        return ResponseEntity.ok(shortUrlService.getShortUrls());
    }

    @Operation(summary = "取得短網址資訊")
    @GetMapping("/info/{shortUrl}")
    public ResponseEntity<ShortUrlInfo> getShortUrlInfo(@PathVariable("shortUrl") String shortUrl) {
        return ResponseEntity.ok(shortUrlService.getShortUrlInfo(shortUrl));
    }

    @Operation(summary = "刪除短網址")
    @DeleteMapping("/delete/{shortUrl}")
    public ResponseEntity<String> deleteShortUrl(@PathVariable("shortUrl") String shortUrl) {
        shortUrlService.deleteShortUrl(shortUrl);
        return ResponseEntity.ok("刪除成功");
    }

    private ResponseEntity<String> generateShortUrl(String url) {
        if (URLShortUtils.isValidUrl(url)) {
            return ResponseEntity.ok(shortUrlService.createShortUrl(url, url));
        }
        return ResponseEntity.badRequest().body("無效的網址");
    }

}
