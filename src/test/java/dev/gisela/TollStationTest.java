package dev.gisela;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TollStationTest {

    private TollStation tollStation;

    @BeforeEach
    public void setUp() {
        tollStation = new TollStation("Central Station", "City X");
    }

    @Test
    public void testRegisterCar() {
        Vehicle car = new Car("ABC123");
        tollStation.registerVehicle(car);

        assertThat(tollStation.getTotalCollected(), is(100));
        assertThat(tollStation.getVehicles(), hasItem(car));
    }

    @Test
    public void testRegisterMotorcycle() {
        Vehicle motorcycle = new Motorcycle("XYZ789");
        tollStation.registerVehicle(motorcycle);

        assertThat(tollStation.getTotalCollected(), is(50));
        assertThat(tollStation.getVehicles(), hasItem(motorcycle));
    }

    @Test
    public void testRegisterTruck() {
        Vehicle truck = new Truck("LMN456", 4);
        tollStation.registerVehicle(truck);

        assertThat(tollStation.getTotalCollected(), is(200));
        assertThat(tollStation.getVehicles(), hasItem(truck));
    }

    @Test
    public void testTotalCollected() {
        Vehicle car = new Car("ABC123");
        Vehicle motorcycle = new Motorcycle("XYZ789");
        Vehicle truck = new Truck("LMN456", 3);

        tollStation.registerVehicle(car);
        tollStation.registerVehicle(motorcycle);
        tollStation.registerVehicle(truck);

        assertThat(tollStation.getTotalCollected(), is(300));
    }

    @Test
    public void testGetVehicles() {
        Vehicle car = new Car("ABC123");
        Vehicle motorcycle = new Motorcycle("XYZ789");

        tollStation.registerVehicle(car);
        tollStation.registerVehicle(motorcycle);

        List<Vehicle> vehicles = tollStation.getVehicles();
        assertThat(vehicles, hasSize(2));
        assertThat(vehicles, contains(car, motorcycle));
    }

    @Test
    public void testPrintReport() {
        Vehicle car = new Car("ABC123");
        Vehicle motorcycle = new Motorcycle("XYZ789");
        tollStation.registerVehicle(car);
        tollStation.registerVehicle(motorcycle);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        tollStation.printReport();

        String output = outputStream.toString();
        assertThat(output, containsString("Toll Station: Central Station in City X"));
        assertThat(output, containsString("License Plate: ABC123, Toll: $100"));
        assertThat(output, containsString("License Plate: XYZ789, Toll: $50"));
        assertThat(output, containsString("Total Collected: $150"));

        System.setOut(System.out);
    }

    @Test
    public void testEmptyTollStation() {
        assertThat(tollStation.getTotalCollected(), is(0));
        assertThat(tollStation.getVehicles(), is(empty()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        tollStation.printReport();

        String output = outputStream.toString();
        assertThat(output, containsString("Toll Station: Central Station in City X"));
        assertThat(output, containsString("Vehicles passed:"));
        assertThat(output, containsString("Total Collected: $0"));

        System.setOut(System.out);
    }
}
