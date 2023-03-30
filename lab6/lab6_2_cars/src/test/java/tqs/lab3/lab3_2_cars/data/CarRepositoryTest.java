package tqs.lab3.lab3_2_cars.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.lab3.lab3_2_cars.entity.Car;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired 
    private TestEntityManager entityManager;

    @Autowired 
    private CarRepository carRepository;

    @Test
    void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findById(-99L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test 
    void whenFindCarByExistingId_thenReturnCar() {
        Car car = new Car("fiat", "500");
        entityManager.persistAndFlush(car);

        Car fromDb = carRepository.findByCarId(car.getCarId()).orElse(null);
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getModel()).isEqualTo( car.getModel());
    } 

    @Test
    void testFindAll() {
        Car corsa = new Car("opel", "corsa");
        Car ibiza = new Car("seat", "ibiza");
        Car panda = new Car("fiat", "panda");
        
        entityManager.persist(corsa);
        entityManager.persist(ibiza);
        entityManager.persist(panda);
        entityManager.flush();

        List<Car> allCars = Arrays.asList(corsa, ibiza, panda);

        assertThat(allCars).hasSize(3).extracting(Car::getMaker).containsOnly(corsa.getMaker(), ibiza.getMaker(), panda.getMaker());
    }
}
