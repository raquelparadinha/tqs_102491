package tqs.lab3.lab3_2_cars;

public class Car {
    private Long carId;
    private String maker;
    private String model;

    public Car() {}

    public Car(String maker, String model) {}

    public Long getCarId() {
        return carId;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car: ID [ " + this.carId + " ] Maker [ " + this.maker +  " ] Model [ " + this.model + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
