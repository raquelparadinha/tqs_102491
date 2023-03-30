package tqs.lab3.lab3_2_cars.boundary;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.lab3.lab3_2_cars.entity.Car;
import tqs.lab3.lab3_2_cars.service.CarManagerService;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarManagerService carService;

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return new ResponseEntity<Car>(carService.save(car), HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }
    
    @GetMapping("/cars/{id}")
    public Object getCarById(@PathVariable Long id) {
        HttpStatus status = HttpStatus.OK;
        Optional<Car> car = carService.getCarDetails(id);
        if (car.isPresent()) return new ResponseEntity<>(car.get(), status);
        else return ResponseEntity.notFound().build() ;
    }
}
