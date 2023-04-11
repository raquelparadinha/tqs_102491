package tqs.homework.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.homework.cache.CacheService;

@RestController
@RequestMapping("/api/cache")
public class CacheAPI {
    @Autowired
    private CacheService service;

    @GetMapping("/stats") 
    public ResponseEntity<Map<String, Integer>> getAllStats() {
        Map<String, Integer> data = service.cacheStats();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        ResponseEntity<Map<String, Integer>> response = ResponseEntity.ok().headers(responseHeaders).body(data);
        return response;
    }
}
