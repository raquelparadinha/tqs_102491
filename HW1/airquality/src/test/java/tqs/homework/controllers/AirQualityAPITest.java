package tqs.homework.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import tqs.homework.models.City;
import tqs.homework.services.NinjaAPIService;

@WebMvcTest(AirQualityAPI.class)
public class AirQualityAPITest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private NinjaAPIService service;

    private City someCity;

    @BeforeEach
    public void setUp(){
        someCity = new City("Bragança", 41.8072, -6.75919, "PT", 170, 20.0, 21.3, 50.8, 158.7, 55.5, 67.3);
    }

    @AfterEach
    public void teardown() {
    }

    @Test
    void currentByCity() throws Exception {
        when(service.getDataByCity(anyString())).thenReturn(Optional.of(someCity));

        mvc.perform(
            get("/api/airquality/current_city?city=Lagoa").content("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("Bragança")))
            .andExpect(jsonPath("$.ccarbon_monoxide", is(20.0)))
            .andExpect(jsonPath("$.aqi", is(170)));
    }

    @Test
    void currentByBadCity() throws Exception {
        when(service.getDataByCity(anyString())).thenReturn(null);

        mvc.perform(
            get("/api/airquality/current_city?city=abc").content("application/json"))
            .andExpect(status().isNotFound());
    }

    @Test
    void currentByCoords() throws Exception {
        when(service.getDataByCoords(anyString(), anyString())).thenReturn(Optional.of(someCity));

        mvc.perform(
            get("/api/airquality/current_coords?lat=50&lon=20").content("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("Bragança")))
            .andExpect(jsonPath("$.ccarbon_monoxide", is(20.0)))
            .andExpect(jsonPath("$.aqi", is(170)));
    }

    @Test
    void currentByBadCoords() throws Exception {
        when(service.getDataByCoords(anyString(), anyString())).thenReturn(null);

        mvc.perform(
            get("/api/airquality/current_coords?lat=ab&lon=cd").content("application/json"))
            .andExpect(status().isNotFound());
    }
}
