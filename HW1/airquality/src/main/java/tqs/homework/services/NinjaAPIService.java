package tqs.homework.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.homework.cache.CacheService;
import tqs.homework.connection.BasicHttpClient;
import tqs.homework.models.City;
import tqs.homework.utils.ConfigUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Service
public class NinjaAPIService {
    @Autowired
    private BasicHttpClient httpClient;

    @Autowired
    private CacheService cacheService;

    private static final String CURRENT_URI = "https://api.api-ninjas.com/v1/airquality";
    private static final String API_KEY = ConfigUtils.getPropertyFromConfig("ninjaapi_key");
    private static final String CITY_URI = "https://api.api-ninjas.com/v1/geocoding";
    private static final String COORDS_URI = "https://api.api-ninjas.com/v1/reversegeocoding";
    private Logger logger = Logger.getLogger(NinjaAPIService.class.getName());

    public Optional<City> getDataByCity(String city) throws URISyntaxException, IOException, ParseException {
        URIBuilder builder = new URIBuilder(CURRENT_URI);
        builder.addParameter("city", city);

        String uri = builder.build().toString();

        return getData(uri, city, null);
    }

    public Optional<City> getDataByCoords(String lat, String lon) throws URISyntaxException, IOException, ParseException {
        URIBuilder builder = new URIBuilder(CURRENT_URI);
        builder.addParameter("lat", lat);
        builder.addParameter("lon", lon); 

        String uri = builder.build().toString();

        return getData(uri, lat, lon);
    }

    private Optional<City> getData(String uri, String param1, String param2) throws ParseException, IOException, URISyntaxException {
        URIBuilder city_builder;
        String name = null, country = null;
        Double latitude = null, longitude = null;
        
        if (param2 == null) {
            city_builder = new URIBuilder(CITY_URI);
            city_builder.addParameter("city", param1);
            name = param1;
        } else {
            city_builder = new URIBuilder(COORDS_URI);
            city_builder.addParameter("lat", param1);
            city_builder.addParameter("lon", param2);
            latitude = (Double) Double.parseDouble(param1);
            longitude = (Double) Double.parseDouble(param2);
        }

        String city_uri = city_builder.build().toString();
        Optional<String> city_data = Optional.of(httpClient.get(city_uri, API_KEY));
        System.out.print(city_data);

        if(city_data != null) {
            JSONObject city_obj = (JSONObject) ((JSONArray) new JSONParser().parse(city_data.get())).get(0);
            if (param2 == null) {
                try {
                    latitude = (Double) city_obj.get("latitude");
                    longitude = (Double) city_obj.get("longitude");
                    country = (String) city_obj.get("country"); 
                } catch (Exception e) {
                    System.err.print("[ERROR1] " + e.getMessage());
                }
            } else {
                try {
                    name = (String) city_obj.get("name"); 
                    country = (String) city_obj.get("country"); 
                } catch (Exception e) {
                    System.err.print("[ERROR2] " + e.getMessage());
                }
            }
        }

        Optional<String> response = cacheService.get(uri);

        if (response.isEmpty()) {
            logger.log(Level.INFO, "[API SERVICE] Accessing external API.");
            response = Optional.of(httpClient.get(uri, API_KEY));
            cacheService.put(uri, response.get());
        }

        Optional<City> data = Optional.empty();

        if(response != null){
            JSONObject obj = (JSONObject) new JSONParser().parse(response.get());

            try {
                int aqi = (Integer) Integer.parseInt(obj.get("overall_aqi").toString());
                double ccarbon_monoxide = (Double) Double.parseDouble(((JSONObject) obj.get("CO")).get("concentration").toString());
                double cnitrogen_dioxide = (Double) Double.parseDouble(((JSONObject)obj.get("NO2")).get("concentration").toString());
                double cozone = (Double) Double.parseDouble(((JSONObject)obj.get("O3")).get("concentration").toString());
                double csulphur_dioxide = (Double) Double.parseDouble(((JSONObject)obj.get("SO2")).get("concentration").toString());
                double cpm2_5 = (Double) Double.parseDouble(((JSONObject)obj.get("PM2.5")).get("concentration").toString());
                double cpm10 = (Double) Double.parseDouble(((JSONObject)obj.get("PM10")).get("concentration").toString()); 
                
                data = Optional.of(new City(name, latitude, longitude, country, aqi, ccarbon_monoxide, cnitrogen_dioxide, cozone, csulphur_dioxide, cpm2_5, cpm10));
            } catch (Exception e) {
                System.err.print("[ERROR3] " + e.getLocalizedMessage());
            }
            
        }

        return data;
    }
}
