package controller;

import dto.TicketRequestDTO;
import dto.TicketResponseDTO;
import model.Ticket;
import model.VehicleType;
import service.TicketService;

public class TicketController {

    TicketService ticketService;
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public TicketResponseDTO createTicket(TicketRequestDTO ticketRequestDTO) {
        String vehicleNumber = ticketRequestDTO.getVehicleNumber();
        VehicleType vehicleType = ticketRequestDTO.getVehicleType();
        long gateID = ticketRequestDTO.getGateId();

        Ticket ticket = null;
        TicketResponseDTO responseDTO = null;
        try {
            ticket = ticketService.processTicketRequest(vehicleNumber, vehicleType, gateID);
        } catch (Exception e) {
            responseDTO = new TicketResponseDTO();
            responseDTO.setStatus("Failure");
            return responseDTO;
        }
        responseDTO = new TicketResponseDTO();
        responseDTO.setTicketID(ticket.getId());
        responseDTO.setOperatorName(ticket.getOperator().getName());
        responseDTO.setParkingSpotNumber(ticket.getParkingSpot().getNumber());
        responseDTO.setStatus("Success");
        return responseDTO;
    }
}
