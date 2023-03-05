package tqs.lab3.lab3_2_cars;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    public Car findByCarId(Long id);

    public List<Car> findAll();
}
