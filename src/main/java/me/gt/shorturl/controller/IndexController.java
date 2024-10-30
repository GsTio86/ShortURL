package me.gt.shorturl.controller;

import me.gt.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin
@RestController
public class IndexController {

    @Autowired
    private ShortUrlService shortUrlService;

    @GetMapping("/{url:[a-zA-Z0-9]{7}}")
    public RedirectView redirectShortUrl(@PathVariable(name = "url") String url) {
        String originalUrl = shortUrlService.getOriginalUrl(url);
        if (originalUrl != null) {
            shortUrlService.addClickCount(url);
            shortUrlService.addVisitCount(url); // 訪問次數 需要額外判定非重複
            return new RedirectView(originalUrl);
        }
        return new RedirectView("/");
    }

}
