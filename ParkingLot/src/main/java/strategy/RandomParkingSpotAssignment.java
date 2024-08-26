package strategy;

import model.*;

import java.util.Optional;

public class RandomParkingSpotAssignment implements IParkingSpotAssignmentStrategy {

    @Override
    public Optional<ParkingSpot> reserveSpot(Vehicle vehicle, Gate gate, ParkingLot parkingLot) {
        for(ParkingFloor floor : parkingLot.getParkingFloors()) {
            for(ParkingSpot spot : floor.getParkingSpots()) {
                if(spot.getStatus() == ParkingSpotStatus.AVAILABLE && spot.getSupportedVehicleTypes().contains(vehicle.getVehicleType())) {
                    return Optional.of(spot);
                }
            }
        }
        return Optional.empty();
    }
}
