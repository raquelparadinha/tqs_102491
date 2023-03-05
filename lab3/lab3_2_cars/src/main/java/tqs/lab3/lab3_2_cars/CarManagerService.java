package tqs.lab3.lab3_2_cars;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class CarManagerService {
    
    @Autowired
    private CarRepository carRepository;

    public Car save(Car car) {
        return null;
    }

    public List<Car> getAllCars() {
        return null;
    }

    public Optional<Car> getCarDetails(Long id) {
        return null;
    }
}
