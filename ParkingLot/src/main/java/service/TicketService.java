package service;

import model.*;
import repository.IGateRepository;
import repository.IParkingLotRepository;
import repository.ITicketRepository;
import repository.IVehicleRepository;
import strategy.IParkingSpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    ITicketRepository ticketRepository;
    IVehicleRepository vehicleRepository;
    IGateRepository gateRepository;
    IParkingLotRepository parkingLotRepository;
    IParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy;
    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType, long gateID) {

        Optional<Gate> gateOptional = gateRepository.fetchByID(gateID);
        if(!gateOptional.isPresent()){
            throw new RuntimeException("");
        }
        Gate gate = gateOptional.get();

        Optional<Vehicle> vehicleOptional = vehicleRepository.fetchByRegistrationNumber(vehicleNumber);
        Vehicle vehicle = null;
        if(vehicleOptional.isPresent()){
            vehicle = vehicleOptional.get();
        } else {
            vehicle = vehicleRepository.persist(new Vehicle(vehicleNumber, vehicleType));
        }
        Optional<ParkingLot> parkingLot = parkingLotRepository.getParkingLotOfGate(
                gate
        );
        if(!parkingLot.isPresent()) {
            throw new RuntimeException("");
        }
        Optional<ParkingSpot> parkingSpotOptional = parkingSpotAssignmentStrategy.reserveSpot(vehicle, gate, parkingLot.get());
        if(!parkingSpotOptional.isPresent()){
            throw new RuntimeException("");
        }

        Ticket newTicket = new Ticket();
        newTicket.setGate(gate);
        newTicket.setOperator(gate.getCurrentOperator());
        newTicket.setParkingSpot(parkingSpotOptional.get());
        newTicket.setVehicle(vehicle);
        newTicket.setEntryTime(new Date());

        newTicket = ticketRepository.persist(newTicket);
        return newTicket;
    }
}