package tqs.homework.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table
public class City {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double latitude, longitude;
    private String country;
    private Integer aqi;
    private Double ccarbon_monoxide, cnitrogen_dioxide, cozone, csulphur_dioxide, cpm2_5, cpm10;

    public City(String name, Double latitude, Double longitude, String country, Integer aqi, Double ccarbon_monoxide, Double cnitrogen_dioxide, Double cozone, Double csulphur_dioxide, Double cpm2_5, Double cpm10) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.aqi = aqi;
        this.ccarbon_monoxide = ccarbon_monoxide;
        this.cnitrogen_dioxide = cnitrogen_dioxide;
        this.cozone = cozone;
        this.csulphur_dioxide = csulphur_dioxide;
        this.cpm2_5 = cpm2_5;
        this.cpm10 = cpm10;
    }
    
    public City(String name) {
        this.name = name;
    }

    public City(Double lat, Double lon) {
        this.latitude = lat;
        this.longitude = lon;
    }

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    } 

    public City(Double lat, Double lon, String country) {
        this.latitude = lat;
        this.longitude = lon;
        this.country = country;
    }

    public City() {}
    
}
