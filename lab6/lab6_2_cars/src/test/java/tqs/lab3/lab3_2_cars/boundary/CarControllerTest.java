package tqs.lab3.lab3_2_cars.boundary;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tqs.lab3.lab3_2_cars.JsonUtils;
import tqs.lab3.lab3_2_cars.entity.Car;
import tqs.lab3.lab3_2_cars.service.CarManagerService;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest()
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;    

    @Test
    public void whenPostCar_thenCreateCar() throws Exception {
        Car car = new Car("mk1", "xpto");

        when(service.save(any())).thenReturn(car);

        mvc.perform(
            post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.model", is("xpto")));

        verify(service, times(1)).save(any());
    }

    @Test
    public void whenGetAllCars_thenReturnJsonArray() throws Exception {
        Car car1 = new Car("mk1", "i8");
        Car car2 = new Car("mk2", "x5");
        Car car3 = new Car("mk3", "Q6");

        List<Car> allCars = Arrays.asList(car1, car2, car3);

        when(service.getAllCars()).thenReturn(allCars);

        mvc.perform(
            get("/api/cars").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].model", is("i8")))
            .andExpect(jsonPath("$[1].model", is("x5")))
            .andExpect(jsonPath("$[2].model", is("Q6")));

        verify(service, times(1)).getAllCars();
    }

    @Test
    public void givenACarId_returnCorrespondentCar() throws Exception {
        Car car = new Car("mk1", "xpto");
        
        when(service.getCarDetails(anyLong())).thenReturn(Optional.of(car));

        mvc.perform(
            get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.model", is("xpto")));

        verify(service, times(1)).getCarDetails(anyLong());        
    }

    @Test
    public void whenInvalidCar_thenReturnNotFound() throws Exception {
        when(service.getCarDetails(anyLong())).thenReturn(Optional.empty());

        mvc.perform(
            get("/api/cars/100"))
            .andExpect(status().isNotFound());

        verify(service, times(1)).getCarDetails(anyLong());

    }
}
