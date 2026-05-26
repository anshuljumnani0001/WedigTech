package com.urlshortener.service;

import com.urlshortener.model.Url;
import com.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository repo;

    public UrlService(UrlRepository repo) {
        this.repo = repo;
    }

    public String shortenUrl(String originalUrl) {
        // Validate URL
        if (originalUrl == null || originalUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be empty");
        }
        if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
            throw new IllegalArgumentException("Invalid URL. Must start with http:// or https://");
        }
        if (originalUrl.length() > 2000) {
            throw new IllegalArgumentException("URL too long");
        }

        // 6 character random code banao
        String code = UUID.randomUUID().toString().substring(0, 6);

        Url url = new Url(code, originalUrl.trim());
        repo.save(url);

        return code;
    }

    public String getOriginalUrl(String code) {
        return repo.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Short URL not found"))
                .getOriginalUrl();
    }
}