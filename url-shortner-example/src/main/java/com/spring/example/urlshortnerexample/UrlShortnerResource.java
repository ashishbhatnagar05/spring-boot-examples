package com.spring.example.urlshortnerexample;


import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/rest/url")
public class UrlShortnerResource {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/{id}")
    public String getURL(@PathVariable String id) {
        String url = stringRedisTemplate.opsForValue().get(id);
        if (url == null) {
            throw new RuntimeException("There is no shorter url for given id");
        }
        System.out.println("URL Retrieved: " + url);
        return url;
    }

    @PostMapping
    public String create(@RequestBody String url) {
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});

        String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();

        if (urlValidator.isValid(url)) {
            System.out.println("Id Generated: " + id);
            stringRedisTemplate.opsForValue().set(id, url);
            return id;
        }

        throw new RuntimeException("URL Invalid:" + url);
    }


}
