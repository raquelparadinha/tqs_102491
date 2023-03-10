package tqs.lab3.lab3_2_cars.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tqs.lab3.lab3_2_cars.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
    public Optional<Car> findByCarId(Long id);

    public List<Car> findAll();
}
