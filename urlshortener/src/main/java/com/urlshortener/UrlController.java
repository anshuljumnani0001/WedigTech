package com.urlshortener.controller;

import com.urlshortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    // POST /api/shorten
    @PostMapping("/api/shorten")
    public ResponseEntity<?> shorten(@RequestBody Map<String, String> body) {
        String url = body.get("url");

        try {
            String code = service.shortenUrl(url);
            String shortUrl = "http://localhost:8080/" + code;
            return ResponseEntity.ok(Map.of(
                    "code", code,
                    "shortUrl", shortUrl
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Server error"));
        }
    }

    // GET /:code — redirect
    @GetMapping("/{code}")
    public ResponseEntity<?> redirect(@PathVariable String code) {
        try {
            String originalUrl = service.getOriginalUrl(code);
            return ResponseEntity.status(302).location(URI.create(originalUrl)).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("error", "Short URL not found"));
        }
    }
}