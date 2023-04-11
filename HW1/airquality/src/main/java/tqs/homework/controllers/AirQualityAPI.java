package tqs.homework.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import tqs.homework.models.City;
import tqs.homework.services.NinjaAPIService;

@RestController
@RequestMapping("/api/airquality")
public class AirQualityAPI {

    @Autowired
    private NinjaAPIService ninjaService;

    private static final String ACCESS = "Access-Control-Allow-Origin";

    @GetMapping("/current_city")
    public ResponseEntity<Optional<City>> getCurrentAirQuality(@RequestParam String city) throws URISyntaxException, IOException, ParseException {
        Optional<City> data;
        ResponseEntity<Optional<City>> response;
        if (ninjaService.getDataByCity(city) != null) {
            data = ninjaService.getDataByCity(city);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(ACCESS, "*");
            response = ResponseEntity.ok().headers(responseHeaders).body(data);
        } else {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(ACCESS, "*");
            response = ResponseEntity.notFound().build();
        }
        
        return response;
    }

    @GetMapping("/current_coords")
    public ResponseEntity<Optional<City>> getCurrentAirQuality(@RequestParam String lat, @RequestParam String lon) throws URISyntaxException, IOException, ParseException {
        Optional<City> data;
        ResponseEntity<Optional<City>> response;
        if (ninjaService.getDataByCoords(lat, lon) != null) {
            data = ninjaService.getDataByCoords(lat, lon);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(ACCESS, "*");
            response = ResponseEntity.ok().headers(responseHeaders).body(data);
        } else {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(ACCESS, "*");
            response = ResponseEntity.notFound().build();
        }

        return response;
    }
} 
