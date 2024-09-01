package repository.impl;

import model.Vehicle;
import repository.IVehicleRepository;

import java.util.Optional;

public class VehicleRepository implements IVehicleRepository {
    @Override
    public Vehicle save(Vehicle vehicle) {
        return null;
    }

    @Override
    public Optional<Vehicle> fetchByRegistrationNumber(String vehicleNumber) {
        return Optional.empty();
    }
}
