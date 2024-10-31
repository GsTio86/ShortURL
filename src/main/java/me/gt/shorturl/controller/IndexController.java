package me.gt.shorturl.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import me.gt.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin
@RestController
public class IndexController {

    @Autowired
    private ShortUrlService shortUrlService;

    @Hidden
    @GetMapping("/{url}")
    public Object redirectShortUrl(@PathVariable(name = "url") String url, HttpServletRequest request) {
        if (url.length() == 7) {
            String originalUrl = shortUrlService.getOriginalUrl(url);
            if (originalUrl != null) {
                shortUrlService.addClickCount(url);
                return new RedirectView(originalUrl);
            }
        }
        return new ModelAndView("NotFound");
    }
}
