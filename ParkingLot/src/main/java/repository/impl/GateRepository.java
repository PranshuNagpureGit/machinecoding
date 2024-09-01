package repository.impl;

import model.Gate;
import repository.IGateRepository;

import java.util.Optional;

public class GateRepository implements IGateRepository {
    @Override
    public Optional<Gate> fetchByID(long gateID) {
        return Optional.empty();
    }
}
