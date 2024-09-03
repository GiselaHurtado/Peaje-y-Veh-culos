package dev.gisela;

import java.util.ArrayList;
import java.util.List;

public class TollStation {

    private String name;
    private String city;
    private int totalCollected;
    private List<Vehicle> vehicles;

    public TollStation(String name, String city) {
        this.name = name;
        this.city = city;
        this.totalCollected = 0;
        this.vehicles = new ArrayList<>();
    }

    public void registerVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        totalCollected += vehicle.calculateToll();
    }

    public int getTotalCollected() {
        return totalCollected;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void printReport() {
        System.out.println("Toll Station: " + name + " in " + city);
        System.out.println("Vehicles passed:");
        for (Vehicle v : vehicles) {
            System.out.println("License Plate: " + v.getLicensePlate() + ", Toll: $" + v.calculateToll());
        }
        System.out.println("Total Collected: $" + totalCollected);
    }
}
