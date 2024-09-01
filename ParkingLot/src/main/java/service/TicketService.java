package service;

import annotation.Transactional;
import model.*;
import repository.*;
import strategy.IParkingSpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    ITicketRepository ticketRepository;
    IVehicleRepository vehicleRepository;
    IGateRepository IGateRepository;
    IParkingLotRepository parkingLotRepository;
    IParkingSpotRepository parkingSpotRepository;
    IParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy;

    public TicketService(ITicketRepository ticketRepository, IVehicleRepository vehicleRepository, repository.IGateRepository IGateRepository, IParkingLotRepository parkingLotRepository, IParkingSpotRepository parkingSpotRepository, IParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy) {
        this.ticketRepository = ticketRepository;
        this.vehicleRepository = vehicleRepository;
        this.IGateRepository = IGateRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingSpotAssignmentStrategy = parkingSpotAssignmentStrategy;
    }

    public Ticket processTicketRequest(String vehicleNumber, VehicleType vehicleType, long gateID) {

        Optional<Gate> gateOptional = IGateRepository.fetchByID(gateID);
        if(!gateOptional.isPresent()){
            throw new RuntimeException("");
        }
        Gate gate = gateOptional.get();

        Optional<Vehicle> vehicleOptional = vehicleRepository.fetchByRegistrationNumber(vehicleNumber);
        Vehicle vehicle = null;
        if(vehicleOptional.isPresent()){
            vehicle = vehicleOptional.get();
        } else {
            vehicle = vehicleRepository.save(new Vehicle(vehicleNumber, vehicleType));
        }
        return reserveSpotAndGenerateTicket(vehicle, gate);
    }

    @Transactional
    public Ticket reserveSpotAndGenerateTicket(Vehicle vehicle, Gate gate) {
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
        ParkingSpot parkingSpot = parkingSpotOptional.get();

        Ticket newTicket = new Ticket();
        newTicket.setGate(gate);
        newTicket.setOperator(gate.getCurrentOperator());
        newTicket.setParkingSpot(parkingSpot);
        newTicket.setVehicle(vehicle);
        newTicket.setEntryTime(new Date());

        newTicket = ticketRepository.save(newTicket);
        parkingSpot.setStatus(ParkingSpotStatus.PARKED);
        parkingSpot.setVehicle(vehicle);
        parkingSpotRepository.save(parkingSpot);
        return newTicket;
    }
}