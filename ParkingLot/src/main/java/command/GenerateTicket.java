package command;

import controller.TicketController;
import dto.TicketRequestDTO;
import model.VehicleType;

import java.util.Arrays;
import java.util.List;

public class GenerateTicket implements Command{
    private static final String COMMAND_NAME = "create_parking_lot";

    TicketController ticketController;

    @Override
    public void execute(String command) {
        List<String> tokens = Arrays.asList(command.split(" "));
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO();

        ticketRequestDTO.setGateId(Long.parseLong(tokens.get(1)));
        ticketRequestDTO.setVehicleNumber(tokens.get(2));
        ticketRequestDTO.setVehicleType(VehicleType.valueOf(tokens.get(3)));

        ticketController.createTicket(ticketRequestDTO);
    }

    @Override
    public boolean matches(String command) {
        List<String> tokens = Arrays.asList(command.split(" "));
        if(tokens.get(0).equals(COMMAND_NAME)) {
            return true;
        }
        return false;
    }
}
