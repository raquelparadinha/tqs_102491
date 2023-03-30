package tqs.lab3.lab3_2_cars.service;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.lab3.lab3_2_cars.data.CarRepository;
import tqs.lab3.lab3_2_cars.entity.Car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {

    @Mock(lenient = true) 
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @BeforeEach
    public void setUp() {
        Car corsa = new Car("opel", "corsa");
        Car ibiza = new Car("seat", "ibiza");
        Car panda = new Car("fiat", "panda");
        Car clio = new Car("renault", "clio");
        
        List<Car> allCars = Arrays.asList(corsa, ibiza, panda, clio);

        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findByCarId(corsa.getCarId())).thenReturn(Optional.of(corsa));
        Mockito.when(carRepository.findByCarId(panda.getCarId())).thenReturn(Optional.of(panda));
        Mockito.when(carRepository.findByCarId(-99L)).thenReturn(Optional.empty());
    }

    @Test
    void testGetAllCars() {
        List<Car> cars = carService.getAllCars();

        assertThat(cars.size()).isEqualTo(4);
        
        assertThat(cars.get(0).getModel()).isEqualTo("corsa");
        assertThat(cars.get(1).getModel()).isEqualTo("ibiza");
        assertThat(cars.get(2).getModel()).isEqualTo("panda");
        assertThat(cars.get(3).getModel()).isEqualTo("clio");

        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testCarDoesntExist() {
        assertThat(carService.getCarDetails(-99L)).isEmpty();

        verify(carRepository, times(1)).findByCarId(-99L);
    }

    @Test
    void testSaveCar() {
        Car car = new Car("toyota", "corolla");

        Mockito.when(carRepository.save(car)).thenReturn(car);

        assertThat(carService.save(car).getModel()).isEqualTo("corolla");
        
        verify(carRepository, times(1)).save(car);
    }
}
