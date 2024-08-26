package repository;

import model.Gate;

import java.util.Optional;

public interface IGateRepository {
    Optional<Gate> fetchByID(long gateID);
}
