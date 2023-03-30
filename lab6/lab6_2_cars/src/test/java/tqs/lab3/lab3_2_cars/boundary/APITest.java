package tqs.lab3.lab3_2_cars.boundary;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import tqs.lab3.lab3_2_cars.data.CarRepository;
import tqs.lab3.lab3_2_cars.entity.Car;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// Uncomment the line bellow to test lab3_2 d)
// @AutoConfigureTestDatabase

// Uncomment the line bellow to test lab3_3
@TestPropertySource(locations = "application-integrationtest.properties")
public class APITest {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired 
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
    void whenValidInput_thenCreateCar() {
        Car fiat = new Car("fiat", "500");
        ResponseEntity<Car> entity = restTemplate
            .postForEntity(("/api/cars"), fiat, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found)
            .extracting(Car::getMaker)
            .containsOnly("fiat");
    }

    @Test
    void whenInvalidId_thenReturnNotFound() {
        ResponseEntity<Car> response = restTemplate
            .getForEntity("/api/cars/100", Car.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void whenGetAllCars_thenStatus200() {
        Car car1 = new Car("mk1", "i8");
        Car car2 = new Car("mk2", "x5");
        repository.saveAndFlush(car1);
        repository.saveAndFlush(car2);

        ResponseEntity<List<Car>> response = restTemplate
            .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("i8", "x5");
    }
}
