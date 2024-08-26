package strategy;

import model.Gate;
import model.ParkingLot;
import model.ParkingSpot;
import model.Vehicle;

import java.util.Optional;

public interface IParkingSpotAssignmentStrategy {
    Optional<ParkingSpot> reserveSpot(Vehicle vehicle, Gate gate, ParkingLot parkingLot);
}
