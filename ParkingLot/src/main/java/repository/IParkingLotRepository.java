package repository;

import model.Gate;
import model.ParkingLot;

import java.util.Optional;

public interface IParkingLotRepository {
    Optional<ParkingLot> getParkingLotOfGate(Gate gate);
}
