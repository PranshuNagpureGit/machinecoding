package repository;

import model.Vehicle;

import java.util.Optional;

public interface IVehicleRepository {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> fetchByRegistrationNumber(String vehicleNumber);
}
