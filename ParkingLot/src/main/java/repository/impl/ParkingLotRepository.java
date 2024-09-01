package repository.impl;

import model.Gate;
import model.ParkingLot;
import repository.IParkingLotRepository;

import java.util.Optional;

public class ParkingLotRepository implements IParkingLotRepository {
    @Override
    public Optional<ParkingLot> getParkingLotOfGate(Gate gate) {
        return Optional.empty();
    }
}
