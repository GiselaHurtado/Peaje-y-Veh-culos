package dev.gisela;

public class Truck extends Vehicle {

    private int axles;

    public Truck(String licensePlate, int axles) {
        super(licensePlate);
        this.axles = axles;
    }

    @Override
    public int calculateToll() {
        return 50 * axles;
    }
}
